package businesslogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Retine date despre un produs compus. Un produs compus contine unul sau mai multe produse din lista meniu.
 * 
 * @author Ducai David
 *
 */
public class CompositeProduct implements MenuItem {

	private String title;
	private double rating;
	
	/** Lista produselor care alcatuiesc produsul compus*/
	private List<MenuItem> items;
	
	
	public CompositeProduct() {
		items = new ArrayList<MenuItem>();
	}
	
	public CompositeProduct(String title, double rating, List<MenuItem> items) {
		this.title = title;
		this.rating = rating;
		this.items = items;
	}
	
	@Override
	public double computePrice() {
		double price = 0;
			
		for(MenuItem item : items) {
			price += item.computePrice();
		}
		
		return price - (price * 0.15); // discount 15%
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public double getRating() {
		return rating;
	}

	@Override
	public String getItemDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("Conține:\n\n");
		for(MenuItem item : items) {
			sb.append(item.getTitle() + "\n");
		}
		return sb.toString();
	}

	public List<MenuItem> getItems() {
		return items;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public void setPrice(double price) {}
}
