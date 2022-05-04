package businesslogic.model;

import java.util.Objects;

/**
 * Retine datele uneui produs de baza.
 * 
 * @author Ducai David
 *
 */
public class BaseProduct implements MenuItem {

	private String title;
	private double rating;
	private int calories;
	private double protein;
	private double fat;
	private double sodium;
	private double price;
	
	
	public BaseProduct() {}
	
	public BaseProduct(String title, double rating, double price) {
		this.title = title;
		this.rating = rating;
		this.price = price;
	}
	
	public BaseProduct(String title, double rating, int calories, double protein, double fat, double sodium, double price) {
		this.title = title;
		this.rating = rating;
		this.calories = calories;
		this.protein = protein;
		this.fat = fat;
		this.sodium = sodium;
		this.price = price;
	}

	@Override
	public double computePrice() {
		return price;
	}
	
	@Override
	public String getItemDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("Informații nutriționale\n\n");
		sb.append("Calorii: " + calories + "\n");
		sb.append("Proteine: " + protein + "\n");
		sb.append("Grasime: " + fat + "\n");
		sb.append("Sodiu: " + sodium + "\n");
		return sb.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseProduct other = (BaseProduct) obj;
		return Objects.equals(title, other.title);
	}
}
