package tony.bank.app.launch;

import java.util.Scanner;

import tony.bank.app.model.*;
import tony.bank.data.structures.List;
import tony.bank.service.interfaces.*;
import tony.bank.service.methods.*;
import tony.bank.app.exceptions.*;

public class AppMain {

	/*
	 * Instantiate the user and account services
	 */

	private static Scanner scanner = new Scanner(System.in);
	private static AccountService accountService = new AccountServiceExec();
	private static UserService userService = new UserServiceExec();

	/*
	 * Instantiate the user and account models.
	 */
	public static User user = new User();
	public static Account account = new Account();

	// MAIN METHOD - INITIATE BANK APPLICATION
	public static void main(String[] args) {

		/*
		 * THE MAIN MENU
		 * 
		 * LOGIN - The user can login with existing credentials.
		 * 
		 * OPEN ACCOUNT - The user can open an account by registering a user name and
		 * password followed by making an initial deposit.
		 * 
		 * EXIT BANK - Terminates the application.
		 */

		mainMenuPrint(); // 1. Login, 2. Register User 3. Exit Bank

		boolean isBanking = true;
		while (isBanking) {

			/*
			 * isLoggedIn, BOOLEAN(FALSE) - If the user is not logged in display the main
			 * menu.
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
				 * isLoggedIn, BOOLEAN(TRUE) - If the user is logged in display the account
				 * menu.
				 * 
				 * CUSTOMER ID - (The id column of the user database, also references the
				 * owner_id in the account table.)
				 * 
				 * ACCOUNT NUMBER - (The id column of the account database, also references the
				 * account_id in the transactions table.)
				 * 
				 * BALANCE - The current available balance of the users account.
				 * 
				 * DEPOSIT - The user is able to make a transaction to deposit funds into their
				 * account.
				 * 
				 * WITHDRAW - The user is able to make a transaction to withdraw funds from
				 * their account, they are not allowed to exceed their balance (overdraft
				 * disabled).
				 * 
				 */

				accountMenuPrint(); // 1. Make Deposit, 2. Make Withdraw, 3. Open Account, 4. Logout

				String input = scanner.nextLine();
				switch (input) {
				case "1":
					openAccount();
					break;
				case "2":
					depositMenu();
					break;
				case "3":
					withdrawMenu();
					break;
				case "4":
					viewAccounts();
					break;
				case "5":
					user.setLoggedIn(false);
					System.out.println("Logging out.");

					mainMenuPrint();

					break;
				}
			}
		}
		System.out.println("Thank you for banking with MyBank Inc.");
		scanner.close();

	}

	/*
	 * REGISTER USER - Creates a user account with a supplied input of a user name
	 * and password.
	 * 
	 * TODO The user name that is supplied is throws error if user name exists, but
	 * doesn't catch and throw a normal message.
	 */

	private static void registerUser() {
		boolean registering = true;

		while (registering) {
			System.out.println("🆔 𝗘𝗻𝘁𝗲𝗿 𝗮 [𝘂𝘀𝗲𝗿𝗻𝗮𝗺𝗲] ");
			String username = scanner.nextLine();

			if (username.isEmpty()) {
				System.out.println("Username field is required!");
				break;
			} else if (!username.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$")) {
				System.out.println("-- USERNAME REQUIREMENTS --\n"
						+ "Alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.\n"
						+ "Allowed the dot (.), underscore (_), and hyphen (-).\n"
						+ "	-must not be the first or last character.\n" + "	-does not appear consecutively\n"
						+ "Must be bewteen 5 to 20.\n");
				mainMenuPrint();
				break;
			}

			System.out.println("🔐 𝗘𝗻𝘁𝗲𝗿 𝗮 [𝗽𝗮𝘀𝘀𝘄𝗼𝗿𝗱] ");

			String password = scanner.nextLine();

			if (password.isEmpty()) {
				System.out.println("Password field is required!");
				break;

			} else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
				System.out.println("-- PASSWORD REQUIREMENTS --\n" + "At least 6 characters\n" + "At least one letter\n"
						+ "At least one number\n");
				mainMenuPrint();
				break;
			}

			System.out.println("👤 𝗘𝗻𝘁𝗲𝗿 𝘆𝗼𝘂𝗿 [𝗙𝘂𝗹𝗹 𝗡𝗮𝗺𝗲] ");

			String name = scanner.nextLine();
			if (name.isEmpty()) {
				System.out.println("Name field is required!");
				mainMenuPrint();
				break;
			}

			System.out.println("📞 𝗘𝗻𝘁𝗲𝗿 𝘆𝗼𝘂𝗿 [𝗣𝗵𝗼𝗻𝗲 #] ");

			String phone = scanner.nextLine();
			if (phone.isEmpty()) {
				System.out.println("Phone is a required field");
				mainMenuPrint();
				break;
			}

			System.out.println("✉️ 𝗘𝗻𝘁𝗲𝗿 𝘆𝗼𝘂𝗿 [𝗘-𝗺𝗮𝗶𝗹] ");

			String email = scanner.nextLine();
			if (email.isEmpty()) {
				System.out.println("Phone is a required field");
				mainMenuPrint();
				break;
			}

			System.out.println("𝗧𝘆𝗽𝗲 [𝘆] 𝘁𝗼 𝗰𝗼𝗻𝗳𝗶𝗿𝗺, [𝗮𝗻𝘆𝘁𝗵𝗶𝗻𝗴 𝗲𝗹𝘀𝗲] 𝘁𝗼 𝗰𝗮𝗻𝗰𝗲𝗹.");
			String input = scanner.nextLine().toLowerCase();

			switch (input) {
			case "y":
				user.setUsername(username);
				user.setPassword(password);
				user.setName(name);
				user.setPhone(phone);
				user.setEmail(email);
				try {
					// register user in the banking system
					user = userService.registerUser(user);

					// Open an account
					openAccount();

					registering = false;
					System.out.println("𝗬𝗼𝘂 𝗺𝗮𝘆 𝗻𝗼𝘄 𝗹𝗼𝗴𝗶𝗻 𝘄𝗶𝘁𝗵 𝘆𝗼𝘂𝗿 𝗰𝗿𝗲𝗱𝗲𝗻𝘁𝗶𝗮𝗹𝘀.");
					mainMenuPrint();
				} catch (UsernameAlreadyExistsException e) {
					System.out.println("𝗧𝗵𝗮𝘁 𝘂𝘀𝗲𝗿 𝗮𝗹𝗿𝗲𝗮𝗱𝘆 𝗲𝘅𝗶𝘀𝘁𝘀 " + ", 𝘁𝗿𝘆 𝗮𝗴𝗮𝗶𝗻");
				}
				break;
			default:
				System.out.println("𝗥𝗲𝘁𝘂𝗿𝗻𝗶𝗻𝗴...");
				registering = false;
			}
		}
	}

	private static User logIn() {
		boolean loggingIn = true;

		while (loggingIn) {
			System.out.println("🆔 𝗘𝗻𝘁𝗲𝗿 𝘆𝗼𝘂𝗿 [𝘂𝘀𝗲𝗿𝗻𝗮𝗺𝗲]");
			String username = scanner.nextLine();

			if (username.isEmpty()) {
				System.out.println("Username field is required!");
				mainMenuPrint();
				break;
			}

			System.out.println("🔐 𝗘𝗻𝘁𝗲𝗿 𝘆𝗼𝘂𝗿 [𝗽𝗮𝘀𝘀𝘄𝗼𝗿𝗱] ");
			String password = scanner.nextLine();

			if (password.isEmpty()) {
				System.out.println("Password field is reuqired!");
				mainMenuPrint();
				break;
			}

			System.out.println("𝗙𝗲𝘁𝗰𝗵𝗶𝗻𝗴 𝘆𝗼𝘂𝗿 𝗮𝗰𝗰𝗼𝘂𝗻𝘁𝘀...");

			user = userService.logIn(username, password);
			if (user == null) {
				System.out.println("𝗖𝗼𝘂𝗹𝗱 𝗻𝗼𝘁 𝗳𝗶𝗻𝗱 𝘆𝗼𝘂𝗿 𝘂𝘀𝗲𝗿 𝗮𝗰𝗰𝗼𝘂𝗻𝘁. ");
				System.out.println("𝗧𝗿𝘆 𝗮𝗴𝗮𝗶𝗻? [𝘆/𝗻]");
				String input = scanner.nextLine().toLowerCase();
				if (!("y".equals(input))) {

					user.setLoggedIn(false);
					loggingIn = false;
				}
			} else {
				userService.getAccountInfo(user);
				return user;
			}
		}
		return user;
	}

	private static void openAccount() {

		boolean opening = true;

		while (opening) {

			Account account = new Account();

			System.out.println("𝗢𝗽𝗲𝗻 𝘄𝗵𝗮𝘁 𝗸𝗶𝗻𝗱 𝗼𝗳 𝗔𝗰𝗰𝗼𝘂𝗻𝘁?\n [𝟭] 𝗖𝗵𝗲𝗰𝗸𝗶𝗻𝗴 𝗔𝗰𝗰𝗼𝘂𝗻𝘁\n [2] Savings\n");
			String accountChoice = scanner.nextLine();

			System.out.println("Initial Deposit: ");

			if (scanner.hasNextDouble()) {
				double deposit = scanner.nextDouble();
				account.setBalance(deposit);

				if (accountChoice.equals("1")) {
					String accountType = "Checking";
					accountService.openAccount(account, user, deposit, accountType);
				} else if (accountChoice.equals("2")) {
					String accountType = "Savings";
					accountService.openAccount(account, user, deposit, accountType);
				} else {
					System.out.println("Must choose an account type!");
					break;
				}

				System.out.println("Account has been opened with an initial balance of " + deposit);
				opening = false;
			} else {
				System.out.println("You can only deposit money! Please try again!");
				break;
			}

		}
	}

	private static User depositMenu() {
		List<Account> accounts = userService.getAccountInfo(user);
		System.out.println("\n🏦 𝗬𝗼𝘂𝗿 𝗔𝗰𝗰𝗼𝘂𝗻𝘁𝘀\n"
				+ "                                                                                                                                      \n");
		System.out.println(accounts);

		System.out.println("[𝗘𝗻𝘁𝗲𝗿 𝗜𝗗] 𝘁𝗼 𝗺𝗮𝗸𝗲 𝘄𝗶𝘁𝗵𝗱𝗿𝗮𝘄𝗮𝗹."
				+ "	[𝗮𝗻𝘆𝘁𝗵𝗶𝗻𝗴 𝗲𝗹𝘀𝗲] 𝘁𝗼 𝗰𝗮𝗻𝗰𝗲𝗹");
		String input = scanner.nextLine();
		Integer id = -1;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("𝗥𝗲𝘁𝘂𝗿𝗻𝗶𝗻𝗴...");
			return user;
		}

		for (int i = 0; i < accounts.size(); i++) {
			Account acc = accounts.get(i);

			if (acc.getId() == id.intValue()) {

				System.out.println("\n                          \n" + "🏦 𝗔𝗰𝗰𝗼𝘂𝗻𝘁# [" + acc.getId() + "]"
						+ "\n💰 𝗕𝗮𝗹𝗮𝗻𝗰𝗲 " + acc.getBalance() + "\n                          \n"
						+ "𝗖𝗼𝗻𝗳𝗶𝗿𝗺 𝗮𝗰𝗰𝗼𝘂𝗻𝘁 [𝘆/𝗻]");

				input = scanner.nextLine().toLowerCase();
				if ("y".equals(input)) {
					System.out.println("𝗘𝗻𝘁𝗲𝗿 [𝗮𝗺𝗼𝘂𝗻𝘁] 𝘁𝗼 𝗱𝗲𝗽𝗼𝘀𝗶𝘁");
					if (scanner.hasNextDouble()) {

						double amount = scanner.nextDouble();

						accountService.makeDeposit(acc, amount);

						System.out.println("☑️ 𝗗𝗲𝗽𝗼𝘀𝗶𝘁 𝗦𝘂𝗰𝗰𝗲𝘀𝘀𝗳𝘂𝗹!");
					} else {
						System.out.println("❌ 𝗪𝗲 𝗼𝗻𝗹𝘆 𝗮𝗰𝗰𝗲𝗽𝘁 𝗺𝗼𝗻𝗲𝘆!");
						break;
					}

				} else {
					System.out.println("𝗥𝗲𝘁𝘂𝗿𝗻𝗶𝗻𝗴 𝘁𝗼 𝘂𝘀𝗲𝗿 𝗺𝗲𝗻𝘂...");
				}
				break;
			}
		}
		return user;

	}

	private static User withdrawMenu() {
		List<Account> accounts = userService.getAccountInfo(user);
		System.out.println("\n🏦 𝗬𝗼𝘂𝗿 𝗔𝗰𝗰𝗼𝘂𝗻𝘁𝘀\n"
				+ "                                                                                                                                      \n");
		System.out.println(accounts);
		System.out.println(
				"[𝗘𝗻𝘁𝗲𝗿 𝗜𝗗] 𝘁𝗼 𝗺𝗮𝗸𝗲 𝗱𝗲𝗽𝗼𝘀𝗶𝘁." + "	[𝗮𝗻𝘆𝘁𝗵𝗶𝗻𝗴 𝗲𝗹𝘀𝗲] 𝘁𝗼 𝗰𝗮𝗻𝗰𝗲𝗹");
		String input = scanner.nextLine();
		Integer id = -1;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("𝗥𝗲𝘁𝘂𝗿𝗻𝗶𝗻𝗴...");
			return user;
		}

		for (int i = 0; i < accounts.size(); i++) {
			Account acc = accounts.get(i);

			if (acc.getId() == id.intValue()) {

				System.out.println("\n                          \n" + "🏦 𝗔𝗰𝗰𝗼𝘂𝗻𝘁# " + acc.getId()
						+ "\n💰 𝗕𝗮𝗹𝗮𝗻𝗰𝗲 " + acc.getBalance() + "\n                          \n"
						+ "𝗖𝗼𝗻𝗳𝗶𝗿𝗺 𝗮𝗰𝗰𝗼𝘂𝗻𝘁 [𝘆/𝗻]");

				input = scanner.nextLine().toLowerCase();
				if ("y".equals(input)) {
					System.out.println("𝗘𝗻𝘁𝗲𝗿 [𝗮𝗺𝗼𝘂𝗻𝘁] 𝘁𝗼 𝘄𝗶𝘁𝗵𝗱𝗿𝗮𝘄");
					if (scanner.hasNextDouble()) {

						double amount = scanner.nextDouble();

						accountService.makeWithdraw(acc, amount);

						System.out.println("☑️ 𝗪𝗶𝘁𝗵𝗱𝗿𝗮𝘄𝗮𝗹 𝗦𝘂𝗰𝗰𝗲𝘀𝘀𝗳𝘂𝗹!");
					} else {
						System.out.println("❌ 𝗪𝗲 𝗼𝗻𝗹𝘆 𝗮𝗰𝗰𝗲𝗽𝘁 𝗺𝗼𝗻𝗲𝘆!");
						break;
					}

				} else {
					System.out.println("𝗥𝗲𝘁𝘂𝗿𝗻𝗶𝗻𝗴 𝘁𝗼 𝘂𝘀𝗲𝗿 𝗺𝗲𝗻𝘂...");
				}
				break;
			}
		}
		return user;

	}
	private static void viewAccounts() {
		// TODO Auto-generated method stub

		List<Account> accounts = userService.getAccountInfo(user);

		System.out.println("\n🏦 𝗬𝗼𝘂𝗿 𝗔𝗰𝗰𝗼𝘂𝗻𝘁𝘀\n"
				+ "                                                                                                                                      \n");
		System.out.println(accounts);
		
		System.out.println("𝗘𝗻𝘁𝗲𝗿 [𝗮𝗻𝘆𝘁𝗵𝗶𝗻𝗴 𝗲𝗹𝘀𝗲] 𝘁𝗼 𝗿𝗲𝘁𝘂𝗿𝗻");

		String input = scanner.nextLine();
		System.out.println("𝗥𝗲𝘁𝘂𝗿𝗻𝗶𝗻𝗴 𝘁𝗼 𝘂𝘀𝗲𝗿 𝗺𝗲𝗻𝘂...");

	}

	private static void mainMenuPrint() {
		System.out.println("\n"
				+ "                                                                                                                                      \n"
				+ "⡷⠂                           🏦 𝐖𝐞𝐥𝐜𝐨𝐦𝐞 𝐭𝐨 𝐌𝐲𝐁𝐚𝐧𝐤 𝐈𝐧𝐜.                             ⠐⢾\n"
				+ "                                                                                                                                      \n");
		System.out.println(" [𝟭] 𝗟𝗼𝗴𝗶𝗻\n" + " [𝟮] 𝗥𝗲𝗴𝗶𝘀𝘁𝗲𝗿 𝗨𝘀𝗲𝗿\n\n" + " [𝟯] 𝗘𝘅𝗶𝘁 𝗕𝗮𝗻𝗸\n"
				+ "\n𝘾𝙝𝙤𝙤𝙨𝙚 𝙖𝙣 𝙤𝙥𝙩𝙞𝙤𝙣\n");
	}

	private static void accountMenuPrint() {
		System.out.println(
				"                                                                                                                                      \n"
			  + "⡷⠂                     🏦 𝐌𝐲𝐁𝐚𝐧𝐤 𝐈𝐧𝐜. - 𝐌𝐲 𝐀𝐜𝐜𝐨𝐮𝐧𝐭                                  ⠐⢾\n"
			  + "                                                                                                                                      \n"
						+ "👤 " + user.getName() + "\n" + "📞 " + user.getPhone() + "\n" + "✉️ " + user.getEmail()
						+ "\n");
		
		System.out.println("🔌𝐀𝐜𝐜𝐨𝐮𝐧𝐭 𝐅𝐮𝐧𝐜𝐭𝐢𝐨𝐧𝐬\n"
				+ "                                                                                                                                      \n");
		System.out.println(" [𝟭] 𝗢𝗽𝗲𝗻 𝗮𝗻 𝗔𝗰𝗰𝗼𝘂𝗻𝘁\n" + " [𝟮] 𝗠𝗮𝗸𝗲 𝗗𝗲𝗽𝗼𝘀𝗶𝘁\n"
				+ " [𝟯] 𝗠𝗮𝗸𝗲 𝗪𝗶𝘁𝗵𝗱𝗿𝗮𝘄𝗮𝗹\n" + " [𝟰] 𝗩𝗶𝗲𝘄 𝗕𝗮𝗹𝗮𝗻𝗰𝗲𝘀\n\n" + " [𝟱] 𝗟𝗼𝗴𝗼𝘂𝘁\n\n [𝗖𝗵𝗼𝗼𝘀𝗲 𝗮𝗻 𝗼𝗽𝘁𝗶𝗼𝗻]\n\n");
	}
}
