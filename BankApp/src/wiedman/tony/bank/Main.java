package wiedman.tony.bank;

// Java packages
import java.util.Scanner;

// My packages
import wiedman.tony.models.User;


public class Main {
	
	//Our main User object. This will act as a "session" object.
	public static User user = new User();
	
	//Our scanner for field inputs
	static Scanner scanner = new Scanner(System.in);
	

	//main method (the initialization of the program)
	public static void main(String[] args) {
		
		// Make sure the account table exists in the database on program initialization
		//SQL sql = new SQL();
		//sql.checkTable();
		
		//welcome to the bank (Begin main menu prompts)
		boolean usingBank = true;
		while (usingBank) {
			if (user.getName() == null) {
				System.out.println(
						  "----------------------------\n"
						+ " | MyBank Inc.\n" 
						+ "----------------------------\n\n"
						+ " 1.] Login to MyBank" 
						+ "\n"
						+ " 2.] Open an Account ($25 Min. Deposit)" 
						+ "\n" 
						+ "\n" 
						+ " 3.] Exit Bank" 
						+ "\n"
						+ "\n Type an option:");
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":	
					// Open the login menu
					user = logIn();
					break;
				case "2":
					// Open the account registration menu
					openAccount();
					break;
				default:
					usingBank = false;
					System.out.println("Thank you for banking with Banking Company Inc.");
				}
			}
			
			// The menu displayed when user has succesfully logged in.
			if (user.getName() != null) {
				System.out.println(
						  "----------------------------\n"
						+ " | MyBank Inc.\n" 
						+ "----------------------------\n\n"
						+ " Welcome back, "+user.getName()+"\n"
						+"\n Account Balance: $" + user.getBalance() + "\n\n"
						+ " 1.] Make Deposit" 
						+ "\n"
						+ " 2.] Make Withdrawal" 
						+ "\n" 
						+ "\n" 
						+ " 3.] Logout" 
						+ "\n"
						+ "\nType an option:");
				
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					System.out.println("Enter Deposit Amount: ");
					double depositAmount = scanner.nextDouble();
					user.makeDeposit(user, depositAmount);
					break;
				case "2":
					System.out.println("Enter Withdrawal Amount: ");
					double withdrawAmount = scanner.nextDouble();
					System.out.println("Not yet implemented.");
					//Main.user.makeWithdrawal(user, withdrawAmount);
					break;
				case "3":
					user.setName(null);
					System.out.println("Logging out.");
				}
			}

		}
		scanner.close();
			

	}
	
	
	// The login menu
	public static User logIn() {
		
		boolean loggingIn = true;
		while (loggingIn) {
		System.out.println("Username: ");
		String username = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();
		
		user.setUsername(username);
		user.setPassword(password);
		
		user.userLogin(user);
		
		if (user.isFailed()) {
			System.out.println("Wrong username or password entered.");
			System.out.println("Try again? y/n");
			String input = scanner.nextLine().toLowerCase();
			
			if (!("y".equals(input))) {
				loggingIn = false;
			}
		} else {
			return user;
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

		
		System.out.println("Type \"y\" to confirm, \"n\" to reset registration, any other key to return.");
		String input = scanner.nextLine().toLowerCase();

		switch (input) {
		case "y":
				user.setName(name);
				user.setUsername(usern);
				user.setPassword(pass);
				user.setBalance(25.00);
				
				// new user overloaded constructor
				user.createAccount(user);
				openingAccount = false;
			break;
		case "n":
			System.out.println("Starting account application over.");
			break;
		default:
			System.out.println("Returning to main menu.");
			openingAccount = false;
		}

		}
	
	}

}

	

	