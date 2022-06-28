package wiedman.tony.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import wiedman.tony.bank.Main;
import wiedman.tony.models.User;

public class SQL {

	// Our database connection variables.
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";
	Connection connection = null;
	Statement statement = null;

	//SQL method for creating a new user in the database
	public User insertUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
			statement = connection.createStatement();

			String sql = "INSERT INTO bank.accounts (NAME, USERNAME, PASSWORD, BALANCE)" + "VALUES ('" + user.getName()
					+ "', '" + user.getUsername() + "', '" + user.getPassword() + "', " + user.getBalance() + ");";

			statement.executeUpdate(sql);

			statement.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Account opened successfully\n\nWelcome to MyBank Inc.");
		return user;
	}

	//SQL method for logging in and checking credentials
	public User selectUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			ResultSet result = statement.executeQuery("SELECT * FROM bank.accounts");

			while (result.next()) {

				// pull data from database and assign to variables
				String nameDB = result.getString("NAME");
				String usernameDB = result.getString("USERNAME");
				String passwordDB = result.getString("PASSWORD");
				double balanceDB = result.getDouble("BALANCE");
				int idDB = result.getInt("ID");

				if ((user.getUsername().equals(usernameDB)) && (user.getPassword().equals(passwordDB))) {
					user.setName(nameDB);
					user.setUsername(usernameDB);
					user.setPassword(passwordDB);
					user.setBalance(balanceDB);
					user.setId(idDB);
					user.setFailed(false);
					user.setLoggedin(true);
				} else {
					user.setFailed(true);
					user.setLoggedin(false);
				}

			}
			result.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return user;

	}

	
	// SQL method for processing deposits and withdrawals and updating the balance in the database.
	public void updateFunds(double amount) {

		DecimalFormat decim = new DecimalFormat("#.00");

		double roundedBalance = Double.parseDouble(decim.format(amount));

		int id = Main.user.getId();

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			String sql = "UPDATE bank.accounts set BALANCE = " + roundedBalance + " where ID=" + id + ";";

			statement.executeUpdate(sql);

			connection.commit();
			statement.close();
			connection.close();

			System.out.println("Deposit of: $ " + amount + " has been received.");
			Main.user.setBalance(roundedBalance);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	//SQL method for checking if the table exists and if not create it (not used anymore good for initializing a table)
		public void checkTable() {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
				statement = connection.createStatement();

				// create table if it doesn't exist
				String sql = "CREATE TABLE IF NOT EXISTS bank.accounts" + "(NAME           varchar(255)    NOT NULL, "
						+ " USERNAME       varchar(255)    NOT NULL, " + " PASSWORD       varchar(255)    NOT NULL, "
						+ " BALANCE		 varchar(255)	 NOT NULL," + " ID  SERIAL PRIMARY KEY)";

				statement.executeUpdate(sql);

				statement.close();
				connection.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}

}
