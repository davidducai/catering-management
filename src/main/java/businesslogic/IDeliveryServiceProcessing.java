package businesslogic;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import businesslogic.model.MenuItem;
import businesslogic.model.Order;

public interface IDeliveryServiceProcessing {

	// General
	
	/**
	 * Returneaza un produs din lista meniu, care are ca valori ale atributelor valorile parametrilor.
	 * 
	 * @precondition title != null && rating >= 0 && price >= 0
	 * @postcondition return != null
	 */
	public MenuItem getItem(String title, double rating, double price);
	
	
	// Administrator
	
	/**
	 * Importeaza in lista meniu produsele de baza din fisierul <code>file</code>.
	 * 
	 * @preconditions file exista si nu este gol.
	 */
	public void importProducts(String file) throws IOException;
	
	/**
	 * Adauga un produs in lista meniu.
	 * 
	 * @precondition item.title != null && item.rating >= 0 && item.price >= 0
	 * @precondition if(item instanceof BaseProduct) item.calories > 0 && item.protein >= 0 && item.fat >= 0 && item.sodium >= 0
	 * @precondition if(item instanceof CompositeProduct) item.items.isEmpty() == false 
	 */
	public void addItem(MenuItem item);
	
	
	/**
	 * Modifica un produs existent in lista meniu.
	 * 
	 * @precondition menuItems.contains(item) && title != null && rating >= 0 && price >= 0
	 */
	public void editItem(MenuItem item, String title, double rating, double price);
	
	
	/**
	 * Sterge un produs din lista meniu.
	 * 
	 * @precondition item != null
	 */
	public void removeItem(MenuItem item);
	
	/** @precondition startTime != null && endTime != null &&
	 * 				  hourPattern.matches(startTime) && hourPattern.matches(endTime), unde hourPattern este formatul de ora HH:MM &&
	 * 				  startTime <= endTime
	 */
	public void generateTimeReport(String startTime, String endTime) throws IOException;
	
	/** @precondition numberOfTimes >= 0*/
	public void generateItemsReport(int numberOfTimes) throws IOException;
	
	/** @precondition numberOfTimes >= 0 && value >= 0 */
	public void generateClientsReport(int numberOfTimes, double value) throws IOException;
	
	/** @precondition date != null */
	public void generateDayReport(Date date) throws IOException;
	

	// Client
	
	/**
	 * Adauga o noua comanda.
	 * 
	 * @precondition order.clientId != null && items.isEmpty() == false
	 */
	public void createOrder(Order order, List<MenuItem> items) throws IOException;
	
	/** Cauta produse in lista de meniu in functie de valorile parametrilor(filtre).
	 *  
	 *  Daca title e "", nu se aplica filtrul pentru titlu.
	 *  Daca rating = -1, nu se aplica filtrul pentru rating.
	 *  Daca price = -1, nu se aplica filtrul pentru pret.
	 *  
	 *  @precondition title != null && (rating >= 0 || rating == -1) && (price >= 0 || price == -1)
	 */
	public List<MenuItem> searchItems(String title, double rating, double price);
}