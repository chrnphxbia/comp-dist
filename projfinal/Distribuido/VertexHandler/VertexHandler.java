/*  
    INTEGRANTES
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432
*/

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class VertexHandler {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(54323);
		System.out.println("Porta 54323 aberta...");

		while(true) {
			Socket socket = server.accept();
			System.out.println("\nCliente " + socket.getInetAddress().getHostAddress() + " conectado");

			DataInputStream messageInput = new DataInputStream(socket.getInputStream());
			String methodRequested = messageInput.readUTF();

			switch (methodRequested) {
				case "insert":
					System.out.println("> Metodo: insertVertex()");
					insertVertex(socket);
					break;

				case "remove":
					System.out.println("> Metodo: removeVertex()");
					removeVertex(socket);
					break;

				default:
					System.out.println("> Metodo nao especificado");
					break;
			}
		
			socket.close();
			System.out.println("Conexao encerrada");
		}
	}

	public static void insertVertex(Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
		Graph graph = (Graph) objectInput.readObject();
		System.out.println("Grafo recebido");

		graph.insereV();
		System.out.println("Vertice inserido");

		ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectOutput.writeObject(graph);
		System.out.println("Grafo enviado");

		objectInput.close();
		objectOutput.close();
	}

	public static void removeVertex(Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
		Graph graph = (Graph) objectInput.readObject();
		System.out.println("Grafo recebido");

		DataInputStream dataInput = new DataInputStream(socket.getInputStream());
		int vertexToBeRemoved = Integer.parseInt(dataInput.readUTF());
		System.out.println("Vertice a ser removido: " + vertexToBeRemoved);

		graph.removeV(vertexToBeRemoved);
		System.out.println("Vertice removido");

		ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectOutput.writeObject(graph);
		System.out.println("Grafo enviado");

		dataInput.close();
		objectInput.close();
		objectOutput.close();
	}
}
