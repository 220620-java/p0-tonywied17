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
	public static User user = new User();
	static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) {
		boolean usingBank = true;

		
		while (usingBank) {
			if (Main.user.getName() == null) {
				System.out.println(
						" Bank Company\n" 
						+ "---------------------\n"
						+ " 1.] Login" 
						+ "\n"
						+ " 2.] Open Account" 
						+ "\n" 
						+ "\n" 
						+ " 3.] Leave the Bank" 
						+ "\n"
						+ "\n Type an option:");
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":	
					Main.user = logIn();
					break;
				case "2":
					openAccount();
					break;
				default:
					usingBank = false;
					System.out.println("Thank you for using Big Bucks Banks!");
				}
			}
			
			//logged in
			
			if (Main.user.getName() != null) {
				System.out.println(
						"\nAccount Balance: $" + Main.user.getBalance() + "\n\n"
						+ "1.] Make Deposit" 
						+ "\n"
						+ "2.] Open Account" 
						+ "\n" 
						+ "\n" 
						+ "3.] Logout" 
						+ "\n"
						+ "\nType an option:");
				
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					System.out.println("Amount: ");
					double depositAmount = scanner.nextDouble();
					Main.user.makeDeposit(user, depositAmount);
					break;
				case "2":
					
					break;
				case "3":
					Main.user.setName(null);
					System.out.println("Logging out.");
				}
			}

		}
		scanner.close();
			

	}
	
	
	public static User logIn() {
		
		boolean loggingIn = true;
		
		
		while (loggingIn) {
		System.out.println("Username: ");
		String username = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();
		
		Main.user.setUsername(username);
		Main.user.setPassword(password);
		
		Main.user.userLogin(Main.user);
		
		if (user==null) {
			System.out.println("Hmm, we couldn't find a user matching those credentials.");
			System.out.println("Do you want to try again? y/n");
			String input = scanner.nextLine().toLowerCase();
			// if they did not say "yes" to trying again
			if (!("y".equals(input))) {
				loggingIn = false;
			}
		} else {
			return Main.user;
		}

	}
		return null;
	}
	
	
	public static void openAccount() {
		
		boolean openingAccount = true;
		while (openingAccount) {
		// add a new user
		System.out.println("Your full name: ");
		String name = scanner.nextLine();
		System.out.println("Enter a username: ");
		String usern = scanner.nextLine();
		System.out.println("Enter a password: ");
		String pass = scanner.nextLine();

		
		System.out.println("Type \"y\" to confirm, \"n\" to try again, or something "
				+ "else to go back.");
		String input = scanner.nextLine().toLowerCase();

		switch (input) {
		case "y":
				Main.user.setName(name);
				Main.user.setUsername(usern);
				Main.user.setPassword(pass);
				Main.user.setBalance(0);
				
				// new user overloaded constructor
				Main.user.createAccount(Main.user);
				openingAccount = false;
			break;
		case "n":
			System.out.println("Okay, let's try again.");
			break;
		default:
			System.out.println("Okay, let's go back.");
			openingAccount = false;
		}

		}
	
	}

}

	

	