package wiedman.tony.bank;

// Java packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

import wiedman.tony.exception.UsernameTakenException;
// My packages
import wiedman.tony.models.User;
import wiedman.tony.service.Lib;


public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) {
		boolean usingBank = true;

		User user = null;
		while (usingBank) {
			if (user == null) {
				System.out.println(
						"1.] Login" 
						+ "\n"
						+ "2.] Open Account" 
						+ "\n" 
						+ "\n" 
						+ "3.] Leave the Bank" 
						+ "\n/"
						+ "\nType an option:");
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					System.out.println("Username: ");
					String username = scanner.nextLine();
					System.out.println("Password: ");
					String password = scanner.nextLine();
					scanner.close();
					
					
					User checkCreds = new User(username, password);
					System.out.println(
							"1.] Make Deposit" 
							+ "\n"
							+ "2.] Open Account" 
							+ "\n" 
							+ "\n" 
							+ "3.] Leave the Bank" 
							+ "\n/"
							+ "\nType an option:");
					String username = scanner.nextLine();
					
					
					break;
				case "2":
					// add a new user
					System.out.println("Your full name: ");
					String name = scanner.nextLine();
					System.out.println("Enter a username: ");
					String usern = scanner.nextLine();
					System.out.println("Enter a password: ");
					String pass = scanner.nextLine();
					System.out.println("Confirm your password: ");
					String confirm = scanner.nextLine();

					// new user overloaded constructor
					User newUser = new User(name, usern, pass, confirm);
					break;
				default:
					usingBank = false;
					System.out.println("Thank you for using Big Bucks Banks!");
				}
			}
		}
			
			

	}

}

	

	