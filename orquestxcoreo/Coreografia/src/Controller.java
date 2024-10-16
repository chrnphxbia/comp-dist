import Services.*;
import Entities.*;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private static void showMenu() {
        System.out.println("\nMENU DE OPCOES");
        System.out.println("0) Ver catalogo de produtos");
        System.out.println("1) Ver itens no carrinho");
        System.out.println("2) Adicionar item ao carrinho");
        System.out.println("3) Remover item do carrinho");
        System.out.println("4) Realizar pedido");
        System.out.println("5) Sair");
    }

    public static void main(String[] args) throws Exception {
        int productID;
        String input = "";
        boolean flag = true;
        String optionSelected = "";
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        ShoppingCart shoppingCart = new ShoppingCart();
        ProductCatalog catalog = new ProductCatalog();
        catalog.loadCatalogFromDatabase("database/catalog.txt");

        showMenu();
        while (flag) {
            System.out.print("\nSelecione uma opcao (! para exibir menu): ");
            optionSelected = scanner.nextLine();

            switch (optionSelected) {
                case "0":
                    catalog.showCatalog();
                    break;
                
                case "1":
                    shoppingCart.showShoppingCart();
                    break;
                
                case "2":
                    System.out.print("\nInsira o ID do item a ser inserido: ");
                    productID = Integer.parseInt(scanner.nextLine());
                    shoppingCart.addToCart(catalog.getProductByID(productID));
                    break;
                
                case "3":
                    System.out.print("\nInsira o ID do item a ser removido: ");
                    productID = Integer.parseInt(scanner.nextLine());
                    shoppingCart.removeFromCart(shoppingCart.getProductByID(productID));
                    break;
                
                case "4":
                    System.out.println("\nGerando pedido...");
                    Order newOrder = new Order(random.nextInt(1000));
                    newOrder.setItems(shoppingCart.getCart());
                    System.out.print("\nInsira seu endereco: ");
                    input = scanner.nextLine();
                    newOrder.setAddress(input);
                    OrderService orderService = new OrderService();
                    orderService.processOrder(newOrder, catalog);
                    break;
                
                case "5":
                    flag = false;
                    System.out.println("Encerrando...");
                    break;
                
                case "!":
                    showMenu();
                    break;
            
                default:
                    System.out.println("\nOpcao invalida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
