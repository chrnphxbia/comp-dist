/*  
    INTEGRANTES
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileNotFoundException;

public class Graph implements Serializable {
    private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Integer adj[][]; // Matriz de adjacência
	private static final long serialVersionUID = 6529685098267757690L;

	// Método Construtor Vazio
	public Graph() {} 

	// Método Construtor
	// Argumentos: Nome do arquivo a ser lido (grafo.txt)
	public Graph(String arquivo) {
		int arestas, origem, destino, peso;

		try {
			Scanner scanner = new Scanner(new File(arquivo));

			this.n = scanner.nextInt(); // Lê linha com número de vértices
			this.adj = new Integer[this.n][this.n]; // Cria matriz com n vértices

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) { // Inicializa matriz com INF
					this.adj[i][j] = Integer.MAX_VALUE;
				}
			}

			arestas = scanner.nextInt(); // Lê número de arestas

			for(int i = 0; i < arestas; i++) {
				origem = scanner.nextInt(); // Lê vértice de origem
				destino = scanner.nextInt(); // Lê vértice de destino
				peso = scanner.nextInt(); // Lê peso da aresta
				this.adj[origem][destino] = peso; // Cria aresta com peso
				this.adj[destino][origem] = peso; // [v][w] = [w][v]
				this.m++; // Atualiza quantidade de arestas
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Getters dos atributos privados do Grafo
	public int getVertices() { return this.n; }
	public int getArestas() { return this.m; }
	public Integer[][] getMatrizAdj() { return this.adj; }

	// Método que guarda os dados do grafo de acordo com formato definido em grafo.txt
	// Argumentos: Nome do arquivo onde serão gravados os dados (grafo.txt)
	public void writeToFile(String fileName) {
		int origem, destino, peso;

		try {
			FileWriter writer = new FileWriter(fileName);

			writer.write(this.n + "\n"); // Escrevendo número de vértices
			writer.write(this.m + "\n"); // Escrevendo número de arestas

			int aux[][] = new int[this.n][this.n]; // Criando matriz auxiliar

			for(int i = 0; i < this.n; i++) { // Inicializando matriz auxiliar
				for(int j = 0; j < this.n; j++) {
					if(i != j) aux[i][j] = 0; 
					else aux[i][j] = 1; // Ignora-se laços
					// Ignora arestas que não existem (inf)
					if(this.adj[i][j] == Integer.MAX_VALUE) aux[i][j] = 1;
				}
			}

			// Como em grafos não direcionados, se [v][w] existe então [w][v]
			// também, é ideal evitar a duplicação de arestas no arquivo.
			// Utiliza-se a matriz auxiliar para verificar se a aresta [v][w]
			// já foi gravada no arquivo quando tentar gravar a aresta [w][v]

			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					// Se aresta ainda não foi gravada
					if(aux[i][j] != 1) { 
						origem = i; // Vértice de origem
						destino = j; // Vértice de destino
						peso = this.adj[origem][destino]; // Peso da aresta

						// Escreve-se "origem destino peso" no arquivo
						writer.write(origem + " " + destino + " " + peso + "\n");

						// Marca aresta como gravada na matriz auxiliar
						aux[i][j] = 1;
						aux[j][i] = 1;
					}
				}
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
