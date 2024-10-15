package Entities;

import java.util.ArrayList;

public class Order {
	private int id;
	private ArrayList<Product> items;

	public Order(int id) {
		this.id = id;
	}

	public Order(int id, ArrayList<Product> items) {
		this.id = id;
		this.items = items;
	}

	public int getID() {
		return this.id;
	}

	public ArrayList<Product> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}
}
