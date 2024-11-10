// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

// Classe que representa um nó na rede P2P
public class NetworkNode {
	private String nodeName;
	private Set<String> files; // Arquivos que o nó possui
	private Set<String> queriedFiles; // Arquivos que o nó já buscou
	private List<NetworkNode> neighbors; // Vizinhos do nó

	public NetworkNode(String nodeName) {
		this.nodeName = nodeName;
		this.files = new HashSet<>();
		this.neighbors = new ArrayList<>();
		this.queriedFiles = new HashSet<>();
	}

	// Adiciona um arquivo ao nó
	public void addFile(String fileName) {
		files.add(fileName);
	}

	// Adiciona um vizinho à lista de vizinhos do nó
	public void addNeighbor(NetworkNode neighbor) {
		neighbors.add(neighbor);
	}

	// Inicia uma consulta para um arquivo
	public void queryFile(String fileName, Set<NetworkNode> visitedNodes, int numOfHops, int maxHops) {
		if (visitedNodes.contains(this)) {
			return; // Evita voltar para o nó que já visitou
		}

		if (numOfHops > maxHops) {
			System.out.println(nodeName + ": TTL expirado.");
			return;
		}

		if (queriedFiles.contains(fileName)) {
			System.out.println(nodeName + " ja consultou por " + fileName + " anteriormente.");
			return;
		}

		System.out.println(nodeName + " está procurando por '" + fileName + "'...");
		queriedFiles.add(fileName);

		if (files.contains(fileName)) {
			System.out.println(nodeName + " encontrou o arquivo '" + fileName + "'!");
			return;
		}

		// Marca o nó como visitado
		visitedNodes.add(this);

		// Repete a consulta para todos os vizinhos
		for (NetworkNode neighbor : neighbors) {
			neighbor.queryFile(fileName, visitedNodes, numOfHops + 1, maxHops);
		}
	}

	// Método para iniciar a busca de um arquivo
	public void searchFile(String fileName, int maxHops) {
		int numOfHops = 0;
		Set<NetworkNode> visitedNodes = new HashSet<>();
		queryFile(fileName, visitedNodes, numOfHops, maxHops);
	}

	// Retorna o nome do nó
	public String getNodeName() {
		return nodeName;
	}
}