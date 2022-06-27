package wiedman.tony.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static DecimalFormat deciFormat = new DecimalFormat();
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";

	public static void main(String[] args) {
		//initialize the application
		bankApp();
		
		//close static scanner
		scanner.close();
	}

	public static void bankApp() {

		boolean userContinue = true;

		while (userContinue) {

			System.out.println("/-------------------------------------------/" + "\n" + "* Welcome To Big Bucks Bank"
					+ "\n" + "/-------------------------------------------/" + "\n" + "1.] Login" + "\n"
					+ "2.] Create Account" + "\n" + "\n" + "[Press anything else to exit]" + "\n/"
					+ "-------------------------------------------/\nType an option:");
			String input = scanner.nextLine();

			switch (input) {
			case "1":
				// login

				System.out.println(
						"" + "/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Login" + "\n"
								+ "/-------------------------------------------/" + "\n" + "Username: ");
				String username = scanner.nextLine();
				System.out.println("Password: ");
				String password = scanner.nextLine();
				userContinue = false;
				loginMenu(username, password);
				break;
			case "2":
				// add a new user
				System.out.println("Your full name: ");
				String name = scanner.nextLine();
				System.out.println("Enter a username: ");
				String username1 = scanner.nextLine();
				System.out.println("Enter a password: ");
				String password1 = scanner.nextLine();
				System.out.println("Confirm your password: ");
				String password2 = scanner.nextLine();

				// new user overloaded constructor
				User newUser = new User(name, username1, password1, password2);
				break;
			default:
				userContinue = false;
				break;
			}

		}

	}

	public static void loginMenu(String username, String password) {

		// Create user object
		User checkedCreds = new User();

		// Check that the table exists, if not create it.
		checkedCreds.checkTable();

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT;");

			String compareInp = username + ":" + password;

			while (rs.next()) {

				int idDB = rs.getInt("id");
				double balanceDB = rs.getDouble("BALANCE");
				String nameDB = rs.getString("NAME");
				String usernameDB = rs.getString("USERNAME");
				String passwordDB = rs.getString("PASSWORD");
				String compareDB = usernameDB + ":" + passwordDB;

				if (compareDB.equals(compareInp)) {
					// assign db account properties to the checkedCreds User object if a user with matching credentials are found
					checkedCreds.setId(idDB);
					checkedCreds.setName(nameDB);
					checkedCreds.setUsername(usernameDB);
					checkedCreds.setPassword(passwordDB);
					checkedCreds.setBalance(balanceDB);

					boolean userContinue = true;

					while (userContinue) {

						System.out.println("/-------------------------------------------/" + "\n"
								+ "* Big Bucks Bank - Account# " + checkedCreds.getId() + " - "
								+ checkedCreds.getUsername() + "\n" + "/-------------------------------------------/"
								+ "\n" + "Balance: $" + checkedCreds.getBalance() + "\n\n" + "1.] Make Deposit" + "\n"
								+ "2.] Make Withdrawal" + "\n" + "\n" + "3.] Logout" + "\n"
								+ "/-------------------------------------------/\nType an option:");
						String input = scanner.nextLine();

						switch (input) {
						case "1":
							// make a deposit
							System.out.println("Enter Amount:");
							double depositAmount = scanner.nextDouble();
							checkedCreds.depositBalance(depositAmount, checkedCreds.getId());
							break;
						case "2":
							// make a withdrawal
							System.out.println("Enter Amount:");
							double withdrawAmount = scanner.nextDouble();
							checkedCreds.withdrawBalance(withdrawAmount, idDB);
							break;
						case "3":
							userContinue = false;
							bankApp();
							break;
						}
					}

				}

			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

}
