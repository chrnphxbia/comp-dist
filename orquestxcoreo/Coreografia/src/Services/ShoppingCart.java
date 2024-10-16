package Services;

import java.util.ArrayList;

import Entities.Product;

public class ShoppingCart {
	private ArrayList<Product> cart;

	public ShoppingCart() {
		this.cart = new ArrayList<Product>();
	}

	public ArrayList<Product> getCart() {
		return this.cart;
	}

	public void addToCart(Product product) {
		this.cart.add(product);
		System.out.println("\nProduto adicionado ao carrinho!");
	}

	public void removeFromCart(Product product) {
		this.cart.remove(product);
		System.out.println("\nProduto removido do carrinho!");
	}

	public Product getProductByID(int productID) {
		for (Product product : this.cart ) {
			if(product.getId() == productID) {
				return product;
			}
		}

		return null;
	}

	public void showShoppingCart() {
		System.out.println("\nCARRINHO DE COMPRAS");
		for ( Product product : this.cart ) {
			System.out.println("ID: " + product.getId() + "; Produto: " 
			+ product.getName() + "; Price: " + product.getPrice() + ";");
		}
	}
}
