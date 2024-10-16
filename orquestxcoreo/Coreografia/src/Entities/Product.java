package Entities;

public class Product {
	private int id;
	private String name;
	private double price;

	public Product(int productID, String productName, double price) {
		this.price = price;
		this.id = productID;
		this.name = productName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int productID) {
		this.id = productID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String productName) {
		this.name = productName;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
