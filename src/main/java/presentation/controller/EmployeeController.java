package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Function;
import java.util.stream.Collectors;

import businesslogic.model.Order;
import businesslogic.DeliveryService;
import businesslogic.model.MenuItem;
import presentation.view.EmployeeView;

@SuppressWarnings("deprecation")
public class EmployeeController implements Observer, ActionListener{

	private EmployeeView view;
	private DeliveryService deliveryService;
	
	public EmployeeController(EmployeeView view, DeliveryService deliveryService) {
		this.view = view;
		this.deliveryService = deliveryService;
		
		this.view.getExitButton().addActionListener(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Order order = (Order) arg;
		
		List<MenuItem> items = deliveryService.getOrders().get(order);
		
		Map<MenuItem, Long> orderedItems = items
				.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		view.getNotificationsArea().append(generateNotificationMessage(order,orderedItems));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.dispose();
	}
	
	private static String generateNotificationMessage(Order order, Map<MenuItem, Long> orderedItems) {
		StringBuilder sb = new StringBuilder();
		String delimiter = "=============================================================================\n";
		sb.append(delimiter);
		sb.append("Comandă nouă! - ");
		sb.append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(order.getOrderDate()) + "\n");
		sb.append("Client: " + order.getClientId() + "\n\n");
		orderedItems.entrySet().forEach(item -> sb.append(item.getKey().getTitle() + "(" + item.getValue() +")\n" ));
		return sb.toString();
	}
}
