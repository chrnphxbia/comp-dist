package Orchestrators;

import Entities.Order;
import Services.*;

public class OrderOrchestrator {
	private Payment paymentService = new Payment();
	private ProductCatalog inventoryService = new ProductCatalog();
	private Notification notificationService =  new Notification();

	public boolean processOrder(Order order) {
		boolean paymentSuccess = this.paymentService.processPayment(order);
		if(!paymentSuccess) {
			System.out.println("Pagamento falhou!");
			return false;
		}

		boolean inventorySuccess = this.inventoryService.updateCatalog(order);
		if(!inventorySuccess) {
			System.out.println("Atualizacao de inventario falhou!");
			return false;
		}

		this.notificationService.sendConfirmation(order);
		System.out.println("Pedido processado com sucesso!");
		return true;
	}	

}
