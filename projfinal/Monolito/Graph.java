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
import java.io.FileNotFoundException;

public class Graph {
    private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Integer adj[][]; // Matriz de adjacência

	// Método Construtor Vazio
	public Graph() {} 

    // Método Construtor
    // Argumentos: Número de vértices do grafo
    public Graph(int n) {  
	    this.n = n;
	    this.m = 0; 
	    this.adj = new Integer[n][n];

		for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                this.adj[i][j] = Integer.MAX_VALUE;	
            }
        }
	}

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

	// Método que insere vértice no grafo
	// Argumentos: Rótulo do vértice a ser adicionado
	public void insereV() {
		Integer[][] novaMatriz = new Integer[this.n + 1][this.n + 1];

		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				novaMatriz[i][j] = this.adj[i][j];
				novaMatriz[j][this.n] = Integer.MAX_VALUE;
			}

			novaMatriz[this.n][i] = Integer.MAX_VALUE;
		}

		novaMatriz[this.n][this.n] = Integer.MAX_VALUE;

		this.adj = novaMatriz;
		this.n++;
	}

    // Método que insere uma aresta no grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino, peso da aresta
	public void insereA(int v, int w, int peso) {
	    if(this.adj[v][w] == Integer.MAX_VALUE ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = peso; // Cria aresta na matriz de adjacência
            this.adj[w][v] = peso; // Se [v][w] = peso, [w][v] = peso
	        this.m++; // Atualiza número de arestas do grafo
	    }
	}

	// Método que remove um vértice do grafo não direcionado
	// Argumentos: Vértice a ser removido
	public void removeV(int vertice) {
		if(vertice >= this.n) {
			System.err.println("Não há vértice " + vertice + " no grafo.\nNão é " +
				"possível realizar remoção.");
			return;
		}

		int row, column;
		Integer novaMatriz[][] = new Integer[this.n-1][this.n-1];

		for(int i = 0; i < this.n - 1; i++) {
			row = i;
			if(i >= vertice) row++; 

			for(int j = 0; j < this.n - 1; j++) {
				column = j;
				if(j >= vertice) column++;
				novaMatriz[i][j] = this.adj[row][column];
			}

		}
	
		this.m = this.m - getDegree(vertice);
		this.n--;
		this.adj = novaMatriz;
	}

    // Método que remove uma aresta do grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino
	public void removeA(int v, int w) {
	    if(this.adj[v][w] != Integer.MAX_VALUE ) { // Verifica se aresta existe
	        this.adj[v][w] = Integer.MAX_VALUE; // Remove aresta da matriz de adjacência
            this.adj[w][v] = Integer.MAX_VALUE; // [v][w] = [w][v]
	        this.m--; // Atualiza número de arestas do grafo
	    }
	}

	// Método que retorna o grau de um vértice no grafo não direcionado
	// Argumentos: vértice a ter seu grau calculado
	public int getDegree(int vertice) {
		int degree = 0;

		for(int j = 0; j < n; j++) {
			if(this.adj[vertice][j] != Integer.MAX_VALUE) {
				degree++;
			}
		}

		return degree;
	}

    // Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
	    System.out.println("Vértices: " + n);
	    System.out.println("Arestas: " + m);
	    for(int i = 0; i < n; i++) {
	    	System.out.println();
	        for(int w = 0; w < n; w++) {
                if(this.adj[i][w] != Integer.MAX_VALUE) {
                    System.out.print("[" + i + "," + w + "] = " + this.adj[i][w] + " ");
                } else {
                    System.out.print("[" + i + "," + w + "] = inf ");
                }
            }
	    }
        System.out.println();
	}

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
