// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.util.Date;
import java.security.MessageDigest;

public class Bloco {
    public String hash;
    public String hashAnterior;
    private String university;
    private String studentName;
    private String course;
    private String graduationDate;
    private long timestamp;
    private int nonce;

    public Bloco(String university, String studentName, String course, String graduationDate, String hashAnterior) {
        this.university = university;
        this.studentName = studentName;
        this.course = course;
        this.graduationDate = graduationDate;
        this.hashAnterior = hashAnterior;
        this.timestamp = new Date().getTime();
        this.hash = calcularHash();
    }

    public String calcularHash() {
        String conteudoParaHash = hashAnterior + Long.toString(timestamp) + Integer.toString(nonce)
                                  + university + studentName + course + graduationDate;
        return aplicarSHA256(conteudoParaHash);
    }

    public static String aplicarSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void minerarBloco(int dificuldade) {
        String alvo = new String(new char[dificuldade]).replace('\0', '0');
        while (!hash.substring(0, dificuldade).equals(alvo)) {
            nonce++;
            hash = calcularHash();
        }
        System.out.println("Bloco minerado com sucesso: " + hash);
    }
    
    public String getUniversity() { return university; }
    public String getStudentName() { return studentName; }
    public String getCourse() { return course; }
    public String getGraduationDate() { return graduationDate; }
}
