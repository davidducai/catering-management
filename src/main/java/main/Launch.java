package main;

import businesslogic.DeliveryService;
import data.DataWriter;
import presentation.controller.LoginController;
import presentation.view.LoginView;

/**
 * Lanseaza aplicatia.
 * 
 * @author Ducai David
 *
 */
public class Launch {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DeliveryService deliveryService = DataWriter.deserialize();
		
		//deliveryService.getMenuItems().clear();
		//deliveryService.getOrders().clear();
		
		LoginView loginView = new LoginView();
		LoginController loginController = new LoginController(loginView, deliveryService);
	}
}
