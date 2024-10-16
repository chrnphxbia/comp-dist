package Orchestrators;

import Entities.Order;
import Services.*;

public class OrderOrchestrator {
	private Payment paymentService;
	private Shipping shippingService;
	private ProductCatalog catalogService;
	private Notification notificationService;

	public OrderOrchestrator(ProductCatalog catalog) {
		this.catalogService = catalog;
		this.paymentService = new Payment();
		this.notificationService = new Notification();
		this.shippingService = new Shipping();
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

		boolean shippingSuccess = this.shippingService.sendShippingConfirmation(order);
		if(!shippingSuccess) {
			System.out.println("Falha no envio do pedido!");
			return false;
		}

		this.notificationService.sendConfirmation(order);
		System.out.println("\nPedido processado com sucesso!");
		return true;
	}	

}
