/*  
    INTEGRANTES
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432
*/

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        int origem, destino, peso; // Recebem input do usuário
        boolean flagControl = true; // Controla laço do menu
        String optionSelected = ""; // Recebe input do usuário
        Scanner scanner = new Scanner(System.in);
        
        Graph graph = requestLoadGraph();

        printMenu();
        while(flagControl) {
            System.out.print("Selecione uma opcao (x para exibir menu de opcoes): ");
            optionSelected = scanner.nextLine();
            System.out.println();

            switch(optionSelected) {
                case "a": // Gravar dados no arquivo grafo.txt
                    requestSaveGraph(graph);
                    System.out.println("Dados gravados\n");
                    break;
                
                case "b": // Mostrar grafo
                    System.out.println("Exibindo dados armazenados no grafo: \n");
                    graph.show();
                    System.out.println();
                    break;
                
                case "c": // Inserir vértice
                    graph = requestInsertVertex(graph);
                    System.out.println("Vertice inserido!\n");
                    break;
                
                case "d": // Inserir aresta
                    System.out.print("Vertice de origem: ");
                    origem = Integer.parseInt(scanner.nextLine());

                    System.out.print("Vertice de destino: ");
                    destino = Integer.parseInt(scanner.nextLine());

                    System.out.print("Peso da aresta: ");
                    peso = Integer.parseInt(scanner.nextLine());

                    graph.insereA(origem, destino, peso);

                    System.out.println("Aresta inserida!\n");
                    break;
                
                case "e": // Remover vértice
                    System.out.print("Vertice a ser removido: ");
                    int vertice = Integer.parseInt(scanner.nextLine());

                    graph = requestRemoveVertex(graph, vertice);

                    System.out.println("Vertice removido!\n");
                    break;

                case "f": // Remover aresta
                    System.out.print("Vertice de origem da aresta: ");
                    origem = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Vertice de destino da aresta: ");
                    destino = Integer.parseInt(scanner.nextLine());

                    graph.removeA(origem, destino);
                    
                    System.out.println("Aresta removida!\n");
                    break;

                case "g": // Encerrar aplicação
                    System.out.println("Encerrando a aplicacao...");
                    flagControl = false;
                    break;
                
                case "x": // Imprimir menu de opções
                    System.out.println();
                    printMenu();
                    break;
            
                default: // Tratar entrada inválida
                    System.out.println("Opcao invalida. Tente novamente.\n");
                    break;
            }
        }

        scanner.close();
    }

    private static Graph requestRemoveVertex(Graph graph, int vertex) throws UnknownHostException, IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 54323);
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
        dataOutput.writeUTF("remove");

        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
        objectOutput.writeObject(graph);

        dataOutput.writeUTF(String.valueOf(vertex));

        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        Graph newGraph = (Graph) objectInput.readObject();

        dataOutput.close();
        objectOutput.close();
        objectInput.close();
        socket.close();

        return newGraph;
    }

    private static Graph requestInsertVertex(Graph graph) throws UnknownHostException, IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 54323);
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
        dataOutput.writeUTF("insert");

        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
        objectOutput.writeObject(graph);

        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        Graph newGraph = (Graph) objectInput.readObject();

        dataOutput.close();
        objectOutput.close();
        objectInput.close();
        socket.close();

        return newGraph;
    }

    private static void requestSaveGraph(Graph graph) throws UnknownHostException, IOException {
        Socket socket = new Socket("127.0.0.1", 54322);
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
        dataOutput.writeUTF("save");

        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
        objectOutput.writeObject(graph);

        dataOutput.close();
        objectOutput.close();
        socket.close();
    }

    private static Graph requestLoadGraph() throws UnknownHostException, IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 54322);
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
        dataOutput.writeUTF("load");

        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
        Graph graph = (Graph) objectInput.readObject();

        dataOutput.close();
        objectInput.close();
        socket.close();

        return graph;
    }

    private static void printMenu() { // Imprime o menu da aplicação
        System.out.println("\\--------------------\\ AraGraph //--------------------//");
        System.out.println("a) Gravar grafo");
        System.out.println("b) Mostrar grafo");
        System.out.println("c) Inserir vertice");
        System.out.println("d) Inserir aresta");
        System.out.println("e) Remover vertice");
        System.out.println("f) Remover aresta");
        System.out.println("g) Encerrar a aplicacao");
    }
}
