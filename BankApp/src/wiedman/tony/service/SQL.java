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

	//
	public void checkTable() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();

			// create table if it doesn't exist
			String sql = "CREATE TABLE IF NOT EXISTS ACCOUNT" + "(NAME           varchar(255)    NOT NULL, "
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

	public User insertUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
			statement = connection.createStatement();

			String sql = "INSERT INTO ACCOUNT (NAME, USERNAME, PASSWORD, BALANCE)" + "VALUES ('" + user.getName()
					+ "', '" + user.getUsername() + "', '" + user.getPassword() + "', " + user.getBalance() + ");";

			statement.executeUpdate(sql);

			statement.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Account opened successfully");
		return user;
	}

	public User selectUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			ResultSet result = statement
					.executeQuery("SELECT * FROM ACCOUNT WHERE USERNAME='" + user.getUsername() + "'");

			while (result.next()) {
				String nameDB = result.getString("NAME");
				String usernameDB = result.getString("USERNAME");
				String passwordDB = result.getString("PASSWORD");
				double balanceDB = result.getDouble("BALANCE");
				int idDB = result.getInt("ID");

				if (Main.user.getPassword().equals(passwordDB)) {
					System.out.println("Account Found!");
					user.setName(nameDB);
					user.setUsername(usernameDB);
					user.setPassword(passwordDB);
					user.setBalance(balanceDB);
					user.setId(idDB);
				} else {
					System.out.println("No account found with those credentials");
					user.setFailed(true);
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

	public void setDeposit(double amount) {
		DecimalFormat decim = new DecimalFormat("#.00");
		double newBalance = Main.user.getBalance() + amount;
		double roundedBalance = Double.parseDouble(decim.format(newBalance));
		int id = Main.user.getId();

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			String sql = "UPDATE ACCOUNT set BALANCE = " + roundedBalance + " where ID=" + id + ";";
			
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

}
