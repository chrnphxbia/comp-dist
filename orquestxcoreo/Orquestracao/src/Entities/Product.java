package Entities;

public class Product {
	private int productID;
	private int numProducts;
	private String productName;

	public Product(int productID, int numProducts, String productName) {
		this.productID = productID;
		this.numProducts = numProducts;
		this.productName = productName;
	}

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getNumProducts() {
		return this.numProducts;
	}

	public void setNumProducts(int numProducts) {
		this.numProducts = numProducts;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
