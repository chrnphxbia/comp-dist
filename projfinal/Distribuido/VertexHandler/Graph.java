/*  
    INTEGRANTES
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432
*/

import java.io.Serializable;

public class Graph implements Serializable {
    private	int n; // Número de vértices
	private	int m; // Número de arestas
	private	Integer adj[][]; // Matriz de adjacência
	private static final long serialVersionUID = 6529685098267757690L;

	// Método Construtor Vazio
	public Graph() {} 

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
}
