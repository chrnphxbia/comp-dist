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

    // Método que insere uma aresta no grafo não direcionado
    // Argumentos: Vértice de origem, vértice de destino, peso da aresta
	public void insereA(int v, int w, int peso) {
	    if(this.adj[v][w] == Integer.MAX_VALUE ) { // Verifica se aresta ainda não existe
	        this.adj[v][w] = peso; // Cria aresta na matriz de adjacência
            this.adj[w][v] = peso; // Se [v][w] = peso, [w][v] = peso
	        this.m++; // Atualiza número de arestas do grafo
	    }
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
}
