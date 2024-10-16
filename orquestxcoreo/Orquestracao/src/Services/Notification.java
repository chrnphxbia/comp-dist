package Services;

import Entities.Order;

public class Notification {
	
	public boolean sendConfirmation(Order order) {
		System.out.println("\nEnviando confirmacao de pedido...");
		System.out.println("Pedido (ID: " + order.getID() + ") confirmado!");
		return true;
	}

}
