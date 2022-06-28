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

			String sql = "INSERT INTO bank.users (NAME, USERNAME, PASSWORD)" + "VALUES ('" + user.getName() + "', '" + user.getUsername() + "', '" + user.getPassword() + "');";
			
			String sql2 = "INSERT INTO bank.accounts (USERNAME, BALANCE)" + "VALUES ('" + user.getUsername() + "', '" + user.getBalance() + "');";

			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
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

			ResultSet result = statement.executeQuery("SELECT * FROM bank.users");

			while (result.next()) {

				// pull data from database and assign to variables
				String nameDB = result.getString("NAME");
				String usernameDB = result.getString("USERNAME");
				String passwordDB = result.getString("PASSWORD");
				
				int idDB = result.getInt("ID");

				if ((user.getUsername().equals(usernameDB)) && (user.getPassword().equals(passwordDB))) {
					selectBalance(user);
					user.setName(nameDB);
					user.setUsername(usernameDB);
					user.setPassword(passwordDB);
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

	
	// get balance
	public User selectBalance(User user) {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			ResultSet result = statement.executeQuery("SELECT * FROM bank.accounts WHERE username='"+user.getUsername()+"'");

			while (result.next()) {

				// pull data from database and assign to variable
				double balanceDB = result.getDouble("BALANCE");

				user.setBalance(balanceDB);

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
	public void updateFunds(User user, double amount) {

		DecimalFormat decim = new DecimalFormat("#.00");

		double roundedBalance = Double.parseDouble(decim.format(amount));

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			String sql = "UPDATE bank.accounts SET balance='"+roundedBalance+"' WHERE username='"+user.getUsername()+"'";
			
			statement.executeUpdate(sql);
			connection.commit();
			statement.close();
			connection.close();

			System.out.println("Deposit of: $ " + amount + " has been received.");
			user.setBalance(roundedBalance);

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
				//connection.createStatement().execute("CREATE SCHEMA accounts");
				// create table if it doesn't exist
				String sql = "CREATE TABLE IF NOT EXISTS bank.accounts" 
				+ "(USERNAME           varchar(255)    NOT NULL, "
				+ " BALANCE		 varchar(255)	 NOT NULL," 
				+ " ID  SERIAL PRIMARY KEY)";

				statement.executeUpdate(sql);

				statement.close();
				connection.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}

}
