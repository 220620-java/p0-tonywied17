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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean usingBank = true;
		System.out.println("Welcome to MyBank!");

		User user = null;

		while (usingBank) {
			if (user == null) {
				System.out.println("What would you like to do?\n" + "1. Log in\n" + "2. Register\n" + "x. Quit");

				String input = scanner.nextLine();

				switch (input) {
				case "1":
					user = logIn();
					break;
				case "2":
					registerUser();
					break;
				default:
					usingBank = false;
					System.out.println("Thank you for banking with MyBank Inc.");
				}
			}

			// if they logged in or were already logged in
			if (user != null) {
				System.out.println("Welcome, Please select an option:");
				System.out.println("1. Open an account\n" + "2. View my accounts\n" + "x. Log out");
				String input = scanner.nextLine();

				switch (input) {
				case "1":
					openAccount();
					break;
				case "2":
					transactionMenu();
					break;
				default:
					System.out.println("Logging out.");
					user = null;
				}
			}
		}
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
			
			user.setUsername(username);
			user.setPassword(password);
			
			user = userService.logIn(username, password);

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

			System.out.println("Initial Deposit: ");
			double deposit = scanner.nextDouble();

			System.out.println("Type \"y\" to confirm, \"n\" to try again, or something " + "else to go back.");
			String confirm = scanner.nextLine().toLowerCase();

			switch (confirm) {
			case "y":
				Account account = new Account();
				account.setBalance(deposit);
				accountService.openAccount(account, user, deposit);
				opening = false;
				System.out.println("Success!");
				break;
			case "n":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				opening = false;
			}
		}
	}

	private static void transactionMenu() {
		List<Account> accounts = accountService.viewAccounts();

		System.out.println("Your accounts:");
		System.out.println(accounts);

		System.out.println("Select an account to make a transaction");
		String input = scanner.nextLine();
		Integer id = -1;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Okay, let's go back, then.");
		}

		for (int i = 0; i < accounts.size(); i++) {
			Account selectedAccount = accounts.get(i);
			if (selectedAccount.getId() == id.intValue()) {
				System.out.println("Select Acc#: " + selectedAccount.getId() + " - Balance: "
						+ selectedAccount.getBalance() + "? y/n");
				input = scanner.nextLine().toLowerCase();
				if ("y".equals(input)) {
					boolean makingTransaction = true;
					System.out.println("Would you like to do with this account?");
					System.out.println("1. Make Deposit");
					System.out.println("2. Make Withdraw");
					System.out.println("[Press anything else to cancel]");
					System.out.println("Type a option:");
					input = scanner.nextLine().toLowerCase();
					while (makingTransaction) {
						switch (input) {
						case "1":
							System.out.println("Enter an amount:");
							double depositAmt = scanner.nextDouble();
							accountService.makeDeposit(selectedAccount, depositAmt);
							break;

						case "2":
							System.out.println("Enter an amount:");
							double withdrawAmt = scanner.nextDouble();
							accountService.makeWithdraw(selectedAccount, withdrawAmt);
							break;
						default:
							makingTransaction = false;
							break;
						}
					}

				} else {
					System.out.println("Okay, not this time.");
				}
				break;
			}
		}
	}

}
