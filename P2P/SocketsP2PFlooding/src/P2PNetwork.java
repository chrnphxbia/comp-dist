import domain.Node;

public class P2PNetwork {
    public static void main(String[] args) {
        Node node1 = new Node("Nó 1");
        Node node2 = new Node("Nó 2");
        Node node3 = new Node("Nó 3");
        Node node4 = new Node("Nó 4");
        Node node5 = new Node("Nó 5");

        node1.addFile("arquivo1.txt");
        node3.addFile("arquivo2.txt");
        node5.addFile("arquivo3.txt");

        // Topologia da rede
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
        node2.addNeighbor(node3);
        node3.addNeighbor(node2);
        node3.addNeighbor(node4);
        node4.addNeighbor(node3);
        node4.addNeighbor(node5);
        node5.addNeighbor(node4);

        System.out.println("Iniciando busca a partir do Nó 1:");
        node1.searchFile("arquivo3.txt");
    }
}
