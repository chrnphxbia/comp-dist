package Services;
import Entities.Order;

public class OrderService {
	
	public boolean processOrder(Order order, ProductCatalog catalog) {
		System.out.println("\nPedido " + order.getID() + " criado");
		Payment paymentService = new Payment();
		return paymentService.processPayment(order, catalog);
	}

}
