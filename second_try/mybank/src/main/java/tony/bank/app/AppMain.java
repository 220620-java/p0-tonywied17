package tony.bank.app;

import java.util.Scanner;

import tony.bank.data.structure.List;
import tony.bank.app.model.*;
import tony.bank.app.exceptions.*;
import tony.bank.service.AccountServiceExec;
import tony.bank.service.UserServiceExec;

public class AppMain {

	private static Scanner scanner = new Scanner(System.in);

	private static UserServiceExec userService = new UserServiceExec();
	private static AccountServiceExec accountService = new AccountServiceExec();

	public static User user = new User();
	public static Account account = new Account();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to MyBank!");
		System.out.println("What would you like to do?\n" + "1. Log in\n" + "2. Register\n");
		boolean isBanking = true;
		while (isBanking) {

			if (!user.isLoggedIn()) {
				
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					user = logIn();
					break;
				case "2":
					registerUser();
					break;
				default:
					isBanking = false;
					System.out.println("Thanks for visiting! See you next time.");

				}

			} else {
				System.out.println("Welcome, Please select an option:");
				System.out.println(account.getBalance());
				System.out.println("1. Open an account\n" + "2. Make Deposit\n" + "3. Make Withdraw\n" + "Type quit to logout");
				String input = scanner.nextLine();
				switch (input) {
				case "1":
					openAccount();
					break;
				case "2":
					depositMenu(account);
					break;
				case "3":
					user.setLoggedIn(false);
					System.out.println("Logging out.");
				}
			}
		}
		System.out.println("Thank you for banking with MyBank Inc.");
		scanner.close();

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
					user = userService.registerUser(user);
					registering = false;
					System.out.println("Success!");
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
			accountService.getAccountInfo(account, user);
			if (user == null) {
				System.out.println("Hmm, we couldn't find a user matching those credentials.");
				System.out.println("Do you want to try again? y/n");
				String input = scanner.nextLine().toLowerCase();
				// if they did not say "yes" to trying again
				if (!("y".equals(input))) {
					loggingIn = false;
				}
			} else {
				return user;
			}
		}
		return null;
	}

	private static void openAccount() {
		boolean opening = true;

		while (opening) {
			Account account = new Account();
			System.out.println("Initial Deposit: ");
			double deposit = scanner.nextDouble();

				account.setBalance(deposit);
				accountService.openAccount(account, user, deposit);
				System.out.println("Success!");
				opening = false;
	
		}
	}

	private static void depositMenu(Account account) {
		boolean makingDeposit = true;

		while(makingDeposit) {
			
		System.out.println("Please enter an amount");
		double depositAmt = scanner.nextDouble();
		
		accountService.makeDeposit(account, depositAmt);
		}
		

		
	}

}
