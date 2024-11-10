// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class NetworkServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            // Create network nodes
            NetworkNode node1 = new NetworkNode("Node 1");
            NetworkNode node2 = new NetworkNode("Node 2");
            NetworkNode node3 = new NetworkNode("Node 3");
            NetworkNode node4 = new NetworkNode("Node 4");
            NetworkNode node5 = new NetworkNode("Node 5");

            // Bind nodes to the registry
            Naming.rebind("Node1", node1);
            Naming.rebind("Node2", node2);
            Naming.rebind("Node3", node3);
            Naming.rebind("Node4", node4);
            Naming.rebind("Node5", node5);

            // Set up neighbors
            node1.addNeighbor(node2);
            node2.addNeighbor(node1);
            node2.addNeighbor(node3);
            node3.addNeighbor(node2);
            node3.addNeighbor(node4);
            node4.addNeighbor(node3);
            node4.addNeighbor(node5);
            node5.addNeighbor(node4);

            // Add files to nodes
            node1.addFile("file1.txt");
            node3.addFile("file2.txt");
            node5.addFile("file3.txt");

            System.out.println("RMI Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
