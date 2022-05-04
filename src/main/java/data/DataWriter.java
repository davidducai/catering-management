package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import businesslogic.DeliveryService;

/**
 * Contine metode pentru serializarea si deserializarea datelor aplicatiei.
 * 
 * @author Ducai David
 *
 */
public class DataWriter {

	private static final String USERS_FILE = "users.txt";
	private static final String DATA_FILE = "data.txt";
	
	/** Adauga un nou utilizator. */
	public static void addUser(String username, String password) throws IOException {
		File usersFile = new File(USERS_FILE);
		FileWriter writer = new FileWriter(usersFile, true);
		
		writer.append("\n");
		writer.append(username);
		writer.append("," + password);
		
		writer.flush();
		writer.close();
	}
	
	/** Salveaza datele principale ale aplicatiei in fisierul "data.txt" */
	public static void serialize(DeliveryService deliveryService) {
		try {
			FileOutputStream dataFile = new FileOutputStream(DATA_FILE);
			ObjectOutputStream outputStream = new ObjectOutputStream(dataFile);
			
			outputStream.writeObject(deliveryService);
			
			outputStream.close();
			dataFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/** Preia datele principale aplicatiei din fisierul "data.txt" */
	public static DeliveryService deserialize() {
		DeliveryService deliveryService = null;
		
		try {
			FileInputStream dataFile = new FileInputStream(DATA_FILE);
			ObjectInputStream inputStream = new ObjectInputStream(dataFile);
			
			deliveryService = (DeliveryService) inputStream.readObject();
			
			inputStream.close();
			dataFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return deliveryService;
	}
}
