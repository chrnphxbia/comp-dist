package Services;

import Entities.Order;

public class Shipping {

	public boolean sendShippingConfirmation(Order order) {
		System.out.println("\nPedido enviado para endereço " + order.getAddress());
		return true;
	}

}
