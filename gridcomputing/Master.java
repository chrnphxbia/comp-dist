// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Master {

	public static int getLeastLoadedIndex(Task[] tasks) {
		int[] loads = new int[tasks.length];

		for(int i = 0; i < tasks.length; i++) {
			loads[i] = tasks[i].getPricesList().size();
		}

		int leastLoadedIndex = 0;

		for(int i = 0; i < loads.length; i++) {
			if(loads[i] < loads[leastLoadedIndex]) {
				leastLoadedIndex = i;
			}
		}

		return leastLoadedIndex;
	}

	public static void main(String[] args) throws IOException {
		int[] workers = {5001, 5002, 5003, 5004};

		ArrayList<Double> prices = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			prices.add(Math.random() * 100000);
		}

		int count = 0;
		Task[] tasks = new Task[4];
		for(int i = 0; i < 4; i++) {
			tasks[i] = new Task();
		}

		for(int i = 0; i < 10; i++) {
			int leastLoadedIndex = getLeastLoadedIndex(tasks);
			System.out.println("Least loaded: " + leastLoadedIndex);
			long numOfPrices = Math.round(Math.random() * 10);
			System.out.println("Prices added: "+ numOfPrices);
			System.out.println();
			
			for(int j = 0; j < numOfPrices; j++) {
				tasks[leastLoadedIndex].addPriceToList(prices.get(count));
				count++;
			}
		}
		System.out.println();

		for(Task tarefa : tasks) {
			System.out.println("Tamanho: "+ tarefa.getPricesList().size());
			for(Double price : tarefa.getPricesList()) {
				System.out.println(String.format("%.2f", price));
			}
			System.out.println();
		}

		for(int i = 0; i < workers.length; i++) {
			try {
				Socket socket = new Socket("localhost", workers[i]);
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(tasks[i]);

				output.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	}
}
