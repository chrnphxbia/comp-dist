package Services;

import Entities.Order;
import Entities.Product;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class ProductCatalog {
	private ArrayList<Product> catalog;

	public ProductCatalog() {
		this.catalog = new ArrayList<Product>();
	}

	public void loadCatalogFromDatabase(String path) {
		double productPrice;
		String productName;
		int productID, lines;

		try {
			Scanner scanner = new Scanner(new File(path));
			lines = scanner.nextInt();

			for(int i = 0; i < lines; i++) {	
				productID = scanner.nextInt();
				productPrice = Double.parseDouble(scanner.next());
				productName = scanner.nextLine().stripLeading();
				
				Product newProduct = new Product(productID, productName, productPrice);
				this.catalog.add(newProduct);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getCatalog() {
		return this.catalog;
	}

	public boolean updateCatalog(Order order) {
		System.out.println("\nAtualizando catalogo...");
		for (Product product : order.getItems()) {
			System.out.println("Produto " + product.getName() + " removido do catalogo");
			this.catalog.remove(product);
		}
		
		Shipping shippingService = new Shipping();
		return shippingService.sendShippingConfirmation(order);
	}

	public Product getProductByID(int productID) {
		for (Product product : this.catalog ) {
			if(product.getId() == productID) {
				return product;
			}
		}

		return null;
	}

	public void showCatalog() {
		System.out.println("\nCATALOGO DE PRODUTOS");
		for ( Product product : this.catalog ) {
			System.out.println("ID: " + product.getId() + "; Produto: " 
			+ product.getName() + "; Price: $" + product.getPrice() + ";");
		}
	}
}
