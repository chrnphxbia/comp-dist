package Services;

import Entities.Order;
import Entities.Product;

public class Payment {
	
	public boolean processPayment(Order order) {
		System.out.println("\nProcessando pagamento...");
		
		for (Product product : order.getItems() ) {
			System.out.println("Voce pagou $" + product.getPrice() + " por " +
			product.getName());
		}

		System.out.println("Pagamento processado.");

		return true;
	}

}
