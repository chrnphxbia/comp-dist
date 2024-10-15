package Services;

import Entities.Order;

public class Notification {
	
	public boolean sendConfirmation(Order order) {
		System.out.println("Enviando confirmacao de pedido...");
		System.out.println("Pedido (ID: " + order.getID() + ") confirmado!");
		return true;
	}

}
