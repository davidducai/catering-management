package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Retine metode pentru autentificarea utilizatorilor.
 * 
 * @author Ducai David
 *
 */
public class User {

	private static final String USERS_FILE = "users.txt";
	
	/** Cauta numele de utilizator <code>username</code> in fisierul de utilizatori. 
	 *  Returneaza <code>true</code> daca numele este utilizat sau <code>false</code> altfel. */
	public static boolean findUsername(String username) throws FileNotFoundException{
		File usersFile = new File(USERS_FILE);
		Scanner scanner = new Scanner(usersFile);
		boolean found = false;
			
		while(scanner.hasNextLine()) {
			String userData = scanner.nextLine();
			if(userData.contains(username)) {
				found = true;
				break;
			}
		}
		scanner.close();
		return found;
	}
	
	/** Cauta in fisierul de utilizatori combinatia <code>username</code> si <code>password</code>
	 *  Returneaza <code>true</code> daca credentialele au fost gasite sau <code>false</code> altfel.*/
	public static boolean verifyCredentials(String username, String password) throws FileNotFoundException {
		File usersFile = new File("users.txt");
		Scanner scanner = new Scanner(usersFile);
		boolean found = false;
		
		while(scanner.hasNextLine()) {
			String userData = scanner.nextLine();
			List<String> credentials = Arrays.asList(userData.split("\\s*,\\s*")); 
			// credentials(0) = username; credentials(1) = password; credentials(2) = ID
			
			if(credentials.get(0).equals(username) && credentials.get(1).equals(password)) {
				found = true;
				break;
			}
		}
		scanner.close();
		return found;
	}
}
