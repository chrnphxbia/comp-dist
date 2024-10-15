import Services.*;
import Entities.*;
import java.util.Random;
import java.util.Scanner;
import Orchestrators.OrderOrchestrator;

public class Controller {
    private static void showMenu() {
        System.out.println("MENU DE OPCOES");
        System.out.println("0) Ver catalogo de produtos");
        System.out.println("1) Ver itens no carrinho");
        System.out.println("2) Adicionar item ao carrinho");
        System.out.println("3) Remover item do carrinho");
        System.out.println("4) Realizar pedido");
        System.out.println("5) Sair\n");
    }

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int productID;
        boolean flag = true;
        String optionSelected = "";
        Scanner scanner = new Scanner(System.in);
        ShoppingCart shoppingCart = new ShoppingCart();
        ProductCatalog catalog = new ProductCatalog();
        catalog.loadCatalogFromDatabase("database/catalog.txt");

        showMenu();
        while (flag) {
            System.out.print("Selecione uma opcao: ");
            optionSelected = scanner.nextLine();

            switch (optionSelected) {
                case "0":
                    catalog.showCatalog();
                    break;
                
                case "1":
                    shoppingCart.showShoppingCart();
                    break;
                
                case "2":
                    System.out.print("Insira o ID do item a ser inserido: ");
                    productID = Integer.parseInt(scanner.nextLine());
                    shoppingCart.addToCart(catalog.getProductByID(productID));
                    break;
                
                case "3":
                    System.out.print("Insira o ID do item a ser removido: ");
                    productID = Integer.parseInt(scanner.nextLine());
                    shoppingCart.removeFromCart(shoppingCart.getProductByID(productID));
                    break;
                
                case "4":
                    System.out.println("Gerando pedido...");
                    OrderOrchestrator orderOrchestrator = new OrderOrchestrator(catalog);
                    Order newOrder = new Order(random.nextInt(1000));
                    newOrder.setItems(shoppingCart.getCart());
                    orderOrchestrator.processOrder(newOrder);
                    break;
                
                case "5":
                    flag = false;
                    System.out.println("Encerrando...");
                    break;
            
                default:
                    System.out.println("Opcao invalida. Tente novamente.\n");
                    break;
            }
        }

        scanner.close();
    }
}
