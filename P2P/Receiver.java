// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
// 172.16.18.167
// 172.16.18.142

public class Receiver {
    private static final int PORT = 5001;
    private static final int BUFFER_SIZE = 4096;
    private static final ArrayList<String> HOSTs = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo a ser recebido: ");
        String fileName = scanner.nextLine();

        HOSTs.add("172.16.18.167");
        HOSTs.add("172.16.18.142");

        for ( String host : HOSTs ) {
            try (Socket socket = new Socket(host, PORT)) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                
                // Envia o nome do arquivo solicitado
                dos.writeUTF(fileName);
                
                // Recebe a resposta do Sender
                String response = dis.readUTF();
                if ("FOUND".equals(response)) {
                    // Cria o arquivo para salvar o conteúdo recebido
                    String prefixedFileName = "recebidos/" + fileName;
                    FileOutputStream fos = new FileOutputStream(prefixedFileName);
                    
                    // Recebe o conteúdo do arquivo
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    while ((bytesRead = dis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    
                    fos.close();
                    System.out.println(host + ": Arquivo recebido.");
                } else {
                    System.out.println(host + ": Arquivo não encontrado.");
                }
                
                dos.close();
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        }
    }
}
