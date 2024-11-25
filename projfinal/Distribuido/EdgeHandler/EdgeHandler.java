import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EdgeHandler {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket server = new ServerSocket(54324);
		System.out.println("Porta 54324 aberta...");

		while(true) {
			Socket socket = server.accept();
			System.out.println("\nCliente " + socket.getInetAddress().getHostAddress() + " conectado");

			DataInputStream messageInput = new DataInputStream(socket.getInputStream());
			String methodRequested = messageInput.readUTF();

			switch (methodRequested) {
				case "insert":
					System.out.println("> Metodo: insertEdge()");
					insertEdge(socket);
					break;

				case "remove":
					System.out.println("> Metodo: removeEdge()");
					removeEdge(socket);
					break;
			}

			socket.close();
			System.out.println("Conexao encerrada");
		}
	}

	public static void insertEdge(Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
		Graph graph = (Graph) objectInput.readObject();
		System.out.println("Grafo recebido");

		DataInputStream dataInput = new DataInputStream(socket.getInputStream());
		int origin = Integer.parseInt(dataInput.readUTF());
		int destiny = Integer.parseInt(dataInput.readUTF());
		int weight = Integer.parseInt(dataInput.readUTF());
		System.out.println("Aresta a ser inserida: " + origin + " " + destiny + " " + weight);

		graph.insereA(origin, destiny, weight);
		System.out.println("Aresta inserida");

		ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectOutput.writeObject(graph);
		System.out.println("Grafo enviado");

		objectInput.close();
		dataInput.close();
		objectOutput.close();
	}

	public static void removeEdge(Socket socket) throws ClassNotFoundException, IOException {
		ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
		Graph graph = (Graph) objectInput.readObject();
		System.out.println("Grafo recebido");

		DataInputStream dataInput = new DataInputStream(socket.getInputStream());
		int origin = Integer.parseInt(dataInput.readUTF());
		int destiny = Integer.parseInt(dataInput.readUTF());
		System.out.println("Aresta a ser removida: " + origin + " " + destiny);

		graph.removeA(origin, destiny);
		System.out.println("Aresta removida");

		ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectOutput.writeObject(graph);
		System.out.println("Grafo enviado");

		objectInput.close();
		dataInput.close();
		objectOutput.close();
	}
}
