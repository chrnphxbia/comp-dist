// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.io.*;
import java.net.*;

public class Worker2 {

	public static void doWork(Task task, double threshold) {
		int count = 0;

		for(Double price : task.getPricesList()) {
			if(price < threshold) {
				System.out.println(count + ". Adicionado: " + String.format("%.2f", price));
			} else {
				System.out.println(count + ". Descartado: " + String.format("%.2f", price));
			}

			count++;
		}

		System.out.println();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try (ServerSocket serverSocket = new ServerSocket(5002)) {
			System.out.println("Worker2 aberto");
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				Task task = (Task) input.readObject();
				doWork(task, 75000);

				input.close();
				socket.close();
			}
		}
	}
}
