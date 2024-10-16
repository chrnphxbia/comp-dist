package Services;

import Entities.Order;
import Entities.Product;

public class Payment {

	public boolean processPayment(Order order, ProductCatalog catalog) {
		System.out.println("\nProcessando pagamento...");
		
		for (Product product : order.getItems() ) {
			System.out.println("Voce pagou $" + product.getPrice() + " por " +
			product.getName());
		}

		System.out.println("Pagamento processado.");

		return catalog.updateCatalog(order);
	}

}
