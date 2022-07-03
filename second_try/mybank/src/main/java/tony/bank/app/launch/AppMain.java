package tony.bank.app.launch;

import java.util.Scanner;

import tony.bank.app.model.*;
import tony.bank.service.interfaces.*;
import tony.bank.service.methods.AccountServiceExec;
import tony.bank.service.methods.UserServiceExec;
import tony.bank.app.exceptions.*;


public class AppMain {

	/*
	 * Instantiate the user and account services
	 */
	
	private static Scanner scanner = new Scanner(System.in);
	private static AccountService accountService = new AccountServiceExec();
	private static UserService userService = new UserServiceExec();
	

	/* 
	 * Instantiate the user and account services.
	 */
	public static User user = new User();
	public static Account account = new Account();
	
	
	
	// MAIN METHOD - INITIATE BANK APPLICATION
	public static void main(String[] args) {
		
		
		/* THE MAIN MENU
		 * 
		 * LOGIN - The use can login with existing credentials.
		 * 
		 * OPEN ACCOUNT - The user can open an account by registering a user name
		 * and password followed by making an initial deposit.
		 * 
		 * EXIT BANK - Terminates the application
		 */
		System.out.println("-------------------------------------");
		System.out.println("Welcome to MyBank!");
		System.out.println("-------------------------------------");
		System.out.println( "1. Log in\n" + "2. Open Account (Deposit Required)\n" + "3. Exit Bank\n" + "\nType an option:\n" );
		boolean isBanking = true;
		while (isBanking) {

			/*
			 * isLoggedIn, BOOLEAN(FALSE) - If the user is not logged in display the main menu.
			 */
			if (!user.isLoggedIn()) {
				
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					user = logIn();
					break;
				case "2":
					registerUser();
					break;
				case "3":
					isBanking = false;
					break;

				}

			} else {
				/*
				 * MY ACCOUNT - USER IS LOGGED IN WITH EXISTING CREDENTIALS
				 * 
				 * CUSTOMER ID - 
				 * 
				 * ACCOUNT NUMBER -
				 * 
				 * BALANCE -
				 * 
				 * DEPOSIT -
				 * 
				 * WITHDRAW -
				 * 
				 * 
				 * 
				 */
				System.out.println("-------------------------------------");
				System.out.println("MyBank - My Account - Customer ID: " + user.getId());
				System.out.println("-------------------------------------");
				System.out.println( "\nAcc#: "+account.getId()+", Balance: " + accountService.convertCurrency(account.getBalance()) + "\n" );
				System.out.println("Welcome, Please select an option:\n");
				System.out.println("1. Make Deposit\n" + "2. Make Withdraw\n" + "3. View Transactions\n\n" + "4. Logout");
				String input = scanner.nextLine();
				switch (input) {
				case "1":
					depositMenu(account);
					break;
				case "2":
					withdrawMenu(account);
					break;
				case "3":
					transactionPrint();
					break;
				case "4":
					user.setLoggedIn(false);
					System.out.println("Logging out.");
					System.out.println("Welcome to MyBank!");
					System.out.println("What would you like to do?\n" + "1. Log in\n" + "2. Register\n" + "3. Exit Bank\n" );
				}
			}
		}
		System.out.println("Thank you for banking with MyBank Inc.");
		scanner.close();

	}

	
	
	private static void transactionPrint() {
		// TODO Auto-generated method stub
		boolean viewTrans = true;
		
		accountService.viewTransactions(account);
		
		while(viewTrans) {
			System.out.println("Type anything to go back\n");
			scanner.nextLine();

			viewTrans = false;
			break;
			
		}
		
	}



	private static void registerUser() {
		boolean registering = true;

		while (registering) {
			System.out.println("Enter a username: ");
			String username = scanner.nextLine();
			System.out.println("Enter a password: ");
			String password = scanner.nextLine();
			System.out.println("Type \"y\" to confirm, \"n\" to try again, or something " + "else to go back.");
			String input = scanner.nextLine().toLowerCase();

			switch (input) {
			case "y":
				user.setUsername(username);
				user.setPassword(password);
				try {
					//register user in the banking system
					user = userService.registerUser(user);
					System.out.println("User account created, awaiting initial deposit.");
					
					//Open an account with an initial deposit
					openAccount();
					
					registering = false;
					System.out.println("You may now login with your credentials.");
					System.out.println("What would you like to do?\n" + "1. Log in\n" + "2. Register\n" + "3. Exit Bank\n" );
				} catch (UsernameAlreadyExistsException e) {
					System.out.println("Oh no, a user with that username already exists. " + "Let's try again.");
				}
				break;
			case "n":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				registering = false;
			}
		}
	}

	
	
	private static User logIn() {
		boolean loggingIn = true;

		while (loggingIn) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			System.out.println("Press enter to login");
			
			user = userService.logIn(username, password);
			if (user == null) {
				System.out.println("Hmm, we couldn't find a user matching those credentials.");
				System.out.println("Do you want to try again? y/n");
				String input = scanner.nextLine().toLowerCase();
				// if they did not say "yes" to trying again
				if (!("y".equals(input))) {
					
					user.setLoggedIn(false);
					loggingIn = false;
				}
			} else {
				accountService.getAccountInfo(account, user);
				return user;
			}
		}
		return user;
	}

	private static void openAccount() {
		boolean opening = true;

		while (opening) {
			Account account = new Account();
			System.out.println("Initial Deposit: ");
			
			
			
			if(scanner.hasNextDouble()) {
				double deposit = scanner.nextDouble();
				account.setBalance(deposit);
				accountService.openAccount(account, user, deposit);
				System.out.println("Account has been opened with an initial balance of " + deposit);
				opening = false;
			}else {
				System.out.println("You can only deposit money! Please try again!");
				break;
			}
	
	
		}
	}

	private static void depositMenu(Account account) {
		boolean makingDeposit = true;

		while(makingDeposit) {
			
		System.out.println("Please enter an amount");
		double amount = scanner.nextDouble();
		
		accountService.makeDeposit(account, amount);
		break;
		}

	}
	
	private static void withdrawMenu(Account account) {
		boolean makingWithdraw = true;

		while(makingWithdraw) {
			
		System.out.println("Please enter an amount");
		double amount = scanner.nextDouble();
		
		accountService.makeWithdraw(account, amount);
		break;
		}

	}

}
