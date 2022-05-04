package businesslogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import businesslogic.model.*;

import data.FileGenerator;

@SuppressWarnings("deprecation")
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

	private HashSet<MenuItem> menuItems;
	private HashMap<Order,List<MenuItem>> orders;
	
	public DeliveryService() {
		this.menuItems = new HashSet<MenuItem>();
		this.orders = new HashMap<Order,List<MenuItem>>();
	}
	
	@Override
	public void importProducts(String file) throws IOException {
		
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
			assert reader.readLine() != null;
			List<List<String>> values = reader.lines()
					.map(line -> Arrays.asList(line.split(",")))
					.distinct()
					.collect(Collectors.toList());
			values.forEach(line -> {
				BaseProduct product = new BaseProduct(
						line.get(0), 					  // title
						Double.parseDouble(line.get(1)),  // rating
						Integer.parseInt(line.get(2)),    // calories
						Double.parseDouble(line.get(3)),  // protein
						Double.parseDouble(line.get(4)),  // fat
						Double.parseDouble(line.get(5)),  // sodium
						Double.parseDouble(line.get(6))); // price
				menuItems.add(product);
			});
		} catch (IOException exception) {
			throw exception;
		}
	}
	
	@Override
	public void addItem(MenuItem item) {
		assert item.getTitle() != null && item.getRating() >= 0 && item.computePrice() >= 0;
		
		if(item instanceof BaseProduct) {
			assert ((BaseProduct) item).getCalories() > 0 && ((BaseProduct) item).getProtein() >= 0 && ((BaseProduct) item).getFat() >= 0 && ((BaseProduct) item).getSodium() >= 0;
		}
		else {
			assert ((CompositeProduct) item).getItems().isEmpty() == false;	
		}
		
		menuItems.add(item);
	}
	
	@Override
	public void editItem(MenuItem item, String title, double rating, double price) {
		assert menuItems.contains(item);
		
		removeItem(item);
		item.setTitle(title);
		item.setRating(rating);
		item.setPrice(price);
		addItem(item);
	}
	
	@Override
	public void removeItem(MenuItem item) {
		assert item != null;
		
		menuItems.remove(item);
	}
	
	@Override
	public void generateTimeReport(String startTime, String endTime) throws IOException {
		Pattern hourPattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
		assert startTime != null && endTime != null && hourPattern.matcher(startTime).matches() && hourPattern.matcher(endTime).matches() && startTime.compareTo(endTime) <= 0;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		
		Map<Order,List<MenuItem>> reportMap = orders
				.entrySet()
				.stream()
				.filter(order -> dateFormat.format(order.getKey().getOrderDate()).compareTo(startTime) >= 0 &&
						dateFormat.format(order.getKey().getOrderDate()).compareTo(endTime) <= 0)
				.collect(Collectors.toMap(order -> order.getKey(), order -> order.getValue()));
		
		FileGenerator.generateTimeReport(startTime, endTime, reportMap);
	}

	@Override
	public void generateItemsReport(int numberOfTimes) throws IOException {
		assert numberOfTimes >= 0;
		
		Map<MenuItem, Long> countedItems = orders
				.entrySet()
				.stream()
				.flatMap(order -> order.getValue().stream()) // obtin o lista cu toate produsele comandate pana acum la un loc
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // grupez produsele cu numarul respectiv de aparitii in lista
		
		List<MenuItem> reportList = countedItems // preiau informatiile necesare
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue() >= numberOfTimes)
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
		
		FileGenerator.generateItemsReport(numberOfTimes, reportList);
	}

	@Override
	public void generateClientsReport(int numberOfTimes, double value) throws IOException {
		assert numberOfTimes >= 0 && value >= 0;
		
		Map<String, Long> clientsOrderCount = orders
				.entrySet()
				.stream()
				.map(order -> order.getKey().getClientId())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		List<String> searchedClients = clientsOrderCount
				.entrySet()
				.stream()
				.filter(client -> client.getValue() >= numberOfTimes)
				.map(client -> client.getKey())
				.collect(Collectors.toList());
		
		FileGenerator.generateClientsReport(numberOfTimes, value, searchedClients);
	}

	@Override
	public void generateDayReport(Date date) throws IOException {
		assert date != null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormat.format(date);
		
		Map<MenuItem, Long> reportMap = orders
				.entrySet()
				.stream()
				.filter(order -> dateFormat.format(order.getKey().getOrderDate()).equals(dateString)) // filtrez in functie de data
				.flatMap(order-> order.getValue().stream()) // obtin o lista cu produsele comandate in ziua respectiva la un loc
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // grupez produsele cu numarul respectiv de aparitii in lista
		
		FileGenerator.generateDayReport(dateString, reportMap);
	}
	
	@Override
	public void createOrder(Order order, List<MenuItem> items) throws IOException {
		assert order.getClientId() != null && items.isEmpty() == false;
		
		orders.put(order, items);
		
		setChanged();
		notifyObservers(order);
		
		FileGenerator.generateBill(order, items);
	}

	@Override
	public List<MenuItem> searchItems(String title, double rating, double price) {
		assert title != null && (rating >= 0 || rating == -1) && (price >= 0 || price == -1);
		
		List<MenuItem> filteredItems = menuItems
			.stream()
			.filter((!title.isEmpty()) ? item -> item.getTitle().toLowerCase().contains(title.toLowerCase()) : item -> true)
			.filter((rating != -1) ? item -> item.getRating() >= rating : item -> true)
			.filter((price != -1) ? item -> item.computePrice() <= price : item -> true)
			.collect(Collectors.toList());
		
		return filteredItems;
	}

	@Override
	public MenuItem getItem(String title, double rating, double price) {
		assert title != null && rating >= 0 && price >= 0;
		
		MenuItem item = searchItems(title,rating,price).get(0);
		
		assert item != null;
		
		return item;
	}
	
	public HashSet<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(HashSet<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public HashMap<Order, List<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Order, List<MenuItem>> orders) {
		this.orders = orders;
	}
}
