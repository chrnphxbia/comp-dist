package Services;

import java.util.ArrayList;

import Entities.Product;

public class ShoppingCart {
	private ArrayList<Product> cart;

	public ShoppingCart() {
		this.cart = new ArrayList<Product>();
	}

	public void addToCart(Product product) {
		this.cart.add(product);
		System.out.println("Produto adicionado ao carrinho!");
	}

	public void removeFromCart(Product product) {
		int indexInCart = this.cart.indexOf(product);
		this.cart.remove(indexInCart);
		System.out.println("Produto removido do carrinho!");
	}

	public Product getProductByID(int productID) {
		for (Product product : this.cart ) {
			if(product.getProductID() == productID) {
				return product;
			}
		}

		return null;
	}

	public void showShoppingCart() {
		System.out.println("CARRINHO DE COMPRAS");
		for ( Product product : this.cart ) {
			System.out.println("ID: " + product.getProductID() + "; Produto: " 
			+ product.getProductName() + ";");
		}
	}
}
