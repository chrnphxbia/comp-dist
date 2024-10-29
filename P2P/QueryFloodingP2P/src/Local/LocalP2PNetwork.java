package Local;

public class LocalP2PNetwork {
    private static final int maxHops = 3;

    public static void main(String[] args) {
        // Criação dos nós
        NetworkNode node1 = new NetworkNode("Nó 1");
        NetworkNode node2 = new NetworkNode("Nó 2");
        NetworkNode node3 = new NetworkNode("Nó 3");
        NetworkNode node4 = new NetworkNode("Nó 4");
        NetworkNode node5 = new NetworkNode("Nó 5");

        // Adiciona arquivos aos nós
        node1.addFile("arquivo1.txt");
        node3.addFile("arquivo2.txt");
        node5.addFile("arquivo3.txt");

        // Criação das conexões entre os nós (topologia da rede)
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
        node2.addNeighbor(node3);
        node3.addNeighbor(node2);
        node3.addNeighbor(node4);
        node4.addNeighbor(node3);
        node4.addNeighbor(node5);
        node5.addNeighbor(node4);

        node1.searchFile("arquivo2.txt", maxHops);
        System.out.println();
        node1.searchFile("arquivo3.txt", maxHops);
        System.out.println();
        node2.searchFile("arquivo3.txt", maxHops);
    }
}
