package Services;

import java.io.File;
import java.util.Scanner;

import Entities.Order;
import Entities.Product;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class ProductCatalog {
	private ArrayList<Product> catalog;

	public ProductCatalog() {
		this.catalog = new ArrayList<Product>();
	}

	public void loadCatalogFromDatabase(String path) {
		String productName;
		int productID, numProducts, lines;

		try {
			Scanner scanner = new Scanner(new File(path));
			lines = scanner.nextInt();

			for(int i = 0; i < lines; i++) {	
				productID = scanner.nextInt();
				numProducts = scanner.nextInt();
				productName = scanner.nextLine();
				
				Product newProduct = new Product(productID, numProducts, productName);
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
		System.out.println("Atualizando catalogo...");
		return true;
	}

	public Product getProductByID(int productID) {
		for (Product product : this.catalog ) {
			if(product.getProductID() == productID) {
				return product;
			}
		}

		return null;
	}

	public void showCatalog() {
		System.out.println("CATALOGO DE PRODUTOS");
		for ( Product product : this.catalog ) {
			System.out.println("ID: " + product.getProductID() + "; Produto: " 
			+ product.getProductName() + "; Unidades: " + product.getNumProducts()
			+ ";");
		}
	}
}
