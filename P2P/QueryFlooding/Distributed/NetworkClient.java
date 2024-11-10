// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.rmi.Naming;

public class NetworkClient {
    public static void main(String[] args) {
        try {
            NetworkNodeInterface node1 = (NetworkNodeInterface) Naming.lookup("rmi://localhost/Node1");
            NetworkNodeInterface node2 = (NetworkNodeInterface) Naming.lookup("rmi://localhost/Node2");

            System.out.println("Starting search on Node 1:");
            node1.searchFile("file2.txt", 3);
            System.out.println();

            System.out.println("Starting search on Node 2:");
            node2.searchFile("file3.txt", 3);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
