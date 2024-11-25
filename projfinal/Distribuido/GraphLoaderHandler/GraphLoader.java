/*  
    INTEGRANTES
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432
*/

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GraphLoader {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(54322);
		System.out.println("Porta 54322 aberta...");

		while(true) {
			Socket socket = server.accept();
			System.out.println("\nCliente " + socket.getInetAddress().getHostAddress() + " conectado");

			DataInputStream messageInput = new DataInputStream(socket.getInputStream());
			String methodRequested = messageInput.readUTF();

			switch (methodRequested) {
				case "load":
					System.out.println("> Metodo: loadGraph()");
					loadGraph(socket);
					break;
				
				case "save":
					System.out.println("> Metodo: saveGraph()");
					saveGraph(socket);
					break;
			
				default:
					System.out.println("> Metodo nao especificado");
					break;
			}
			
			socket.close();
			System.out.println("Conexao encerrada");
		}
	}

	public static void saveGraph(Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		Graph graph = (Graph) input.readObject();
		System.out.println("Grafo recebido");

		graph.writeToFile("grafo.txt");
		System.out.println("Grafo salvo");

		input.close();
	}

	public static void loadGraph(Socket socket) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		Graph graph = new Graph("grafo.txt");
		System.out.println("Grafo carregado");
			
		output.writeObject(graph);
		System.out.println("Grafo enviado");

		output.close();
	}
}
