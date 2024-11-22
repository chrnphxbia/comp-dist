// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.io.*;
import java.net.*;

public class PeerClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            
            // Define diploma details
            String university = "Universidade ABC";
            String studentName = "João Silva";
            String course = "Engenharia";
            String graduationDate = "01/12/2022";

            // Send diploma data to the server
            out.writeObject(university);
            out.writeObject(studentName);
            out.writeObject(course);
            out.writeObject(graduationDate);
            out.flush();

            // Receive blockchain validity status from the server
            boolean isValid = (boolean) in.readObject();
            System.out.println("Validade: " + isValid);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
