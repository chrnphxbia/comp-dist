package Entities;

import java.util.ArrayList;

public class Order {
	private int id;
	private String address;
	private ArrayList<Product> items;

	public Order(int id) {
		this.id = id;
	}

	public Order(int id, ArrayList<Product> items) {
		this.id = id;
		this.items = items;
	}

	public Order(int id, String address, ArrayList<Product> items) {
		this.id = id;
		this.items = items;
		this.address = address;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Product> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}
}
