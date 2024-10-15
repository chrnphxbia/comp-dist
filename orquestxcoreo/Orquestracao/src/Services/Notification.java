package Services;

import Entities.Order;

public class Notification {
	
	public boolean sendConfirmation(Order order) {
		System.out.println("Enviando confirmacao de pedido...");
		return true;
	}

}
