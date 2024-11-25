/*  
    INTEGRANTES
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432
*/

import java.util.Scanner;

public class Main {

    private static void printMenu(String filePath) { // Imprime o menu da aplicação
        System.out.println("\\\\--------------------\\\\ AraGraph //--------------------//");
        System.out.println("a) Gravar dados no arquivo " + filePath);
        System.out.println("b) Mostrar grafo");
        System.out.println("c) Inserir vértice");
        System.out.println("d) Inserir aresta");
        System.out.println("e) Remover vértice");
        System.out.println("f) Remover aresta");
        System.out.println("g) Encerrar a aplicação");
    }

    public static void main(String[] args) {
        int origem, destino, peso; // Recebem input do usuário
        boolean flagControl = true; // Controla laço do menu
        String optionSelected = ""; // Recebe input do usuário
        String filePath = "temp.txt"; // Variável de caminho do arquivo
        Scanner scanner = new Scanner(System.in);
        
        Graph graph = new Graph(filePath);

        printMenu(filePath);
        while(flagControl) {
            System.out.print("Selecione uma opção (x para exibir menu de opções): ");
            optionSelected = scanner.nextLine();
            System.out.println();

            switch(optionSelected) {
                case "a": // Gravar dados no arquivo grafo.txt
                    graph.writeToFile(filePath);
                    System.out.println("Dados gravados em " + filePath + "!\n");
                    break;
                
                case "b": // Mostrar grafo
                    System.out.println("Exibindo dados armazenados no grafo: \n");
                    graph.show();
                    System.out.println();

                    break;
                
                case "c": // Inserir vértice
                    graph.insereV();
                    System.out.println("Vértice inserido!\n");
                    break;
                
                case "d": // Inserir aresta
                    System.out.print("Vértice de origem: ");
                    origem = Integer.parseInt(scanner.nextLine());

                    System.out.print("Vértice de destino: ");
                    destino = Integer.parseInt(scanner.nextLine());

                    System.out.print("Peso da aresta: ");
                    peso = Integer.parseInt(scanner.nextLine());

                    graph.insereA(origem, destino, peso);

                    System.out.println("Aresta inserida!\n");
                    break;
                
                case "e": // Remover vértice
                    System.out.print("Vértice a ser removido: ");
                    int vertice = Integer.parseInt(scanner.nextLine());

                    graph.removeV(vertice);

                    System.out.println("Vértice removido!\n");
                    break;

                case "f": // Remover aresta
                    System.out.print("Vértice de origem da aresta: ");
                    origem = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Vértice de destino da aresta: ");
                    destino = Integer.parseInt(scanner.nextLine());

                    graph.removeA(origem, destino);
                    
                    System.out.println("Aresta removida!\n");
                    break;

                case "g": // Encerrar aplicação
                    System.out.println("Encerrando a aplicação...");
                    flagControl = false;
                    break;
                
                case "x": // Imprimir menu de opções
                    System.out.println();
                    printMenu(filePath);
                    break;
            
                default: // Tratar entrada inválida
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }

        scanner.close();
    }
}
