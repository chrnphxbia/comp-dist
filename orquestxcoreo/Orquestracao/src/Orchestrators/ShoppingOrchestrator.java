package Orchestrators;

import Services.ProductCatalog;
import Services.ShoppingCart;

public class ShoppingOrchestrator {
	private ShoppingCart cart;
	private ProductCatalog catalog;

	public ShoppingOrchestrator(String path) {
		this.cart = new ShoppingCart();
		this.catalog = new ProductCatalog();
		catalog.loadCatalogFromDatabase(path);
	}

	public boolean showCatalog() {
		this.catalog.showCatalog();
		return true;
	}

	public boolean showShoppingCart() {
		this.cart.showShoppingCart();
		return true;
	}

	public boolean addItemToShoppingCart(int productID) {
		this.cart.addToCart(this.cart.getProductByID(productID));
		return true;
	}

	public boolean removeItemFromShoppingCart(int productID) {
		this.cart.removeFromCart(this.cart.getProductByID(productID));
		return true;
	}
}
