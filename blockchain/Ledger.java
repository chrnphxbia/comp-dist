// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Ledger {
    public static ArrayList<Bloco> blockchain = new ArrayList<>();
    public static int dificuldade = 5;
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Servidor rodando na 8080...");
            
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
                    
                    System.out.println("Cliente conectado.");
                    
                    String university = (String) in.readObject();
                    String studentName = (String) in.readObject();
                    String course = (String) in.readObject();
                    String graduationDate = (String) in.readObject();

                    String previousHash = blockchain.isEmpty() ? "0" : blockchain.get(blockchain.size() - 1).hash;
                    
                    Bloco newBlock = new Bloco(university, studentName, course, graduationDate, previousHash);
                    System.out.println("Minerando bloco...");
                    newBlock.minerarBloco(dificuldade);
                    
                    blockchain.add(newBlock);
                    
                    boolean isValid = ehBlockchainValida();
                    out.writeObject(isValid);
                    out.flush();
                    
                    System.out.println("Validade: " + isValid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
