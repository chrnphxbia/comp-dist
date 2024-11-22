// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Bloco> blockchain = new ArrayList<>();
    public static int dificuldade = 5;

    public static void main(String[] args) {
        blockchain.add(new Bloco("Universidade ABC", "João Silva", "Engenharia", "01/12/2022", "0"));
        System.out.println("Minerando bloco 1...");
        blockchain.get(0).minerarBloco(dificuldade);

        blockchain.add(new Bloco("Universidade XYZ", "Maria Souza", "Medicina", "15/06/2023", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Minerando bloco 2...");
        blockchain.get(1).minerarBloco(dificuldade);

        System.out.println("\nA Blockchain é válida: " + ehBlockchainValida());
    }

    // Validate the blockchain
    public static boolean ehBlockchainValida() {
        Bloco blocoAtual;
        Bloco blocoAnterior;
        String hashAlvo = new String(new char[dificuldade]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            blocoAtual = blockchain.get(i);
            blocoAnterior = blockchain.get(i - 1);

            if (!blocoAtual.hash.equals(blocoAtual.calcularHash())) {
                System.out.println("Hashes do bloco atual não são iguais.");
                return false;
            }
            if (!blocoAnterior.hash.equals(blocoAtual.hashAnterior)) {
                System.out.println("Hashes do bloco anterior não são iguais.");
                return false;
            }
            if (!blocoAtual.hash.substring(0, dificuldade).equals(hashAlvo)) {
                System.out.println("Este bloco não foi minerado.");
                return false;
            }
        }
        return true;
    }
}
