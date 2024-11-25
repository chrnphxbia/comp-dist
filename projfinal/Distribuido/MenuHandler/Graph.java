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

    // Método que exibe número de vértices, arestas e matriz de adjacência
	public void show() {
	    System.out.println("Vertices: " + n);
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
}
