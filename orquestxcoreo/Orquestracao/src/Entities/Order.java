package Entities;

import java.util.ArrayList;

public class Order {
	private int id;
	private ArrayList<String> items;

	public Order(int id) {
		this.id = id;
	}

	public Order(int id, ArrayList<String> items) {
		this.id = id;
		this.items = items;
	}

	public int getID() {
		return this.id;
	}

	public ArrayList<String> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
}
