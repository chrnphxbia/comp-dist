package Orchestrators;

import Entities.Order;
import Services.*;

public class OrderOrchestrator {
	private Payment paymentService;
	private ProductCatalog catalogService;
	private Notification notificationService;

	public OrderOrchestrator(ProductCatalog catalog) {
		this.catalogService = catalog;
		this.paymentService = new Payment();
		this.notificationService = new Notification();
	}

	public boolean processOrder(Order order) {
		boolean paymentSuccess = this.paymentService.processPayment(order);
		if(!paymentSuccess) {
			System.out.println("Pagamento falhou!");
			return false;
		}

		boolean catalogSuccess = this.catalogService.updateCatalog(order);
		if(!catalogSuccess) {
			System.out.println("Atualizacao de catalogo falhou!");
			return false;
		}

		this.notificationService.sendConfirmation(order);
		System.out.println("Pedido processado com sucesso!");
		return true;
	}	

}
