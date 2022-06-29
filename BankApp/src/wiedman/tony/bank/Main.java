package wiedman.tony.bank;

// Java packages
import java.util.Scanner;
import wiedman.tony.models.User;
//import wiedman.tony.service.SQL;

public class Main {

	public static User user = new User();
	static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) throws Exception {

		// Table creator
		// SQL.createTable();

		// Not logged in - Main Screen
		boolean usingBank = true;
		while (usingBank) {
			if (!user.isLoggedin()) {
				System.out.flush();
				System.out.println(
				  "----------------------------------------------------\n" 
				+ " * MyBank Inc. - Welcome Back!\n"
				+ "----------------------------------------------------\n\n" 
				+ " 1.] Login to MyBank" + "\n"
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
					logIn();
					break;
				case "2":
					// Open the account registration menu
					openAccount();
					break;
				default:
					usingBank = false;
					System.out.println("Thank you for banking with MyBank Inc.");
				}
			}
			// The menu displayed when user has successfully logged in.
			else {
				System.out.flush();
				System.out.println(	
			    "----------------------------------------------------\n" 
			  + " * MyBank Inc. - Your Account\n"
			  + "----------------------------------------------------\n" 
			  + " Welcome back, " + user.getName()+"\n" 
			  + "\n Account No: "+user.getAccountNumber()+" - "+user.getAccountType()+" Account\n"
			  + "\n Account Balance: $" + user.getBalance() 
			  + "\n\n" 
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
					user.makeWithdraw(user, withdrawAmount);
					break;
				case "3":
					user.setLoggedin(false);
					System.out.println("Logging out.");
				}
			}

		}
		scanner.close();
	}
	
	
	
	// The login menu
	public static User logIn() throws Exception {
		boolean loggingIn = true;
		while (loggingIn) {
			System.out.flush();
			System.out.println("Username: ");
			String username = scanner.nextLine();
			user.setUsername(username);
			System.out.println("Password: ");
			String password = scanner.nextLine();
			user.setPassword(password);
			user.userLogin(user);
			
			if (!user.isFailed()) {
				loggingIn = false;
			} else {
				loggingIn = false;
				System.out.println("Could not find an account with supplied credentials.\n[Press anything to return to home]");
				scanner.nextLine();
			}
		}
		return user;
	}
	
	
	
	// Create/open account menu
	public static void openAccount() throws Exception {
		boolean openingAccount = true;
		while (openingAccount) {
			// add a new user
			System.out.println("Account Type: \n"
					+ " 1.] Checking\n"
					+ " 2.] Savings");
			
			String type = scanner.nextLine();
			
			
			switch (type) {
			case "1":
				user.setAccountType("Checking");
				break;
			case "2":
				user.setAccountType("Savings");
				break;
			}
			System.out.println("Enter your full name:");
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


