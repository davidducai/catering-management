package businesslogic.model;

import java.io.Serializable;

/**
 * Defineste metode specifice pentru un produs din lista meniu.
 * 
 * @author Ducai David
 *
 */
public interface MenuItem extends Serializable {

	/** Returneaza denumirea produsului. */
	public String getTitle();
	
	/** Returneaza ratingul produsului. */
	public double getRating();
	
	/** Calculeaza pretul produsului. */
	public double computePrice();
	
	/** Returneaza informatii despre produs. */
	public String getItemDescription();
	
	public void setTitle(String title);
	
	public void setRating(double rating);
	
	public void setPrice(double price);
}
