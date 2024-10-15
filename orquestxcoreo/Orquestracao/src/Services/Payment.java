package Services;

import Entities.Order;

public class Payment {
	
	public boolean processPayment(Order order) {
		System.out.println("Processando pagamento...");
		return true;
	}

}
