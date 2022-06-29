package wiedman.tony.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import wiedman.tony.models.User;

public class SQL {
	public static DB db = new DB();
	// SQL method for creating a new user in the database
	public static User insertUser(User user) throws SQLException {
		db.connectDB();
		Statement statement = db.connection.createStatement();
		// insert into bank.users table
		String usersQuery = "INSERT INTO bank.users (NAME, USERNAME, PASSWORD)" 
		+ "VALUES ('" + user.getName() + "', '" + user.getUsername() + "', '" + user.getPassword() + "');";
		// insert into bank.accounts table
		String accountQuery = "INSERT INTO bank.accounts (USERNAME, BALANCE)" + "VALUES ('" + user.getUsername() + "', '" + user.getBalance() + "');";
		statement.executeUpdate(usersQuery);
		statement.executeUpdate(accountQuery);
		db.connection.commit();
		statement.close();
		db.closeDB();
		return user;
	}
	// SQL method for logging in and checking credentials
	public static User selectUser(User user) {
		try {
			db.connectDB();
			Statement statement = db.connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bank.users");
			while (result.next()) {
				// pull data from database and assign to variables
				String nameDB = result.getString("NAME");
				String usernameDB = result.getString("USERNAME");
				String passwordDB = result.getString("PASSWORD");
				int idDB = result.getInt("ID");
				if ((user.getUsername().equals(usernameDB)) && (user.getPassword().equals(passwordDB))) {
					// account was found now lets apply the DB data to the objects variables
					selectBalance(user);
					user.setName(nameDB);
					user.setUsername(usernameDB);
					user.setPassword(passwordDB);
					user.setId(idDB);
					user.setFailed(false);
					user.setLoggedin(true);
				} else {
					// user not found set booleans failed login to true and logged in state to false
					user.setFailed(true);
					user.setLoggedin(false);
				}
			}
			result.close();
			statement.close();
			db.closeDB();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return user;
	}

	// get the users balance from the DB and apply it to their user object
	public static User selectBalance(User user) {
		try {
			db.connectDB();
			Statement statement = db.connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bank.accounts WHERE username='" + user.getUsername() + "'");
			while (result.next()) {
				double balanceDB = result.getDouble("BALANCE");
				user.setBalance(balanceDB);
			}
			result.close();
			statement.close();
			db.closeDB();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return user;
	}
	// SQL method for processing deposits and withdrawals and updating the balance
	// in the database.
	public static void updateFunds(User user, double amount) {
		// decimal formatter
		DecimalFormat decim = new DecimalFormat("#.00");
		// apply format
		double formattedBalance = Double.parseDouble(decim.format(amount));
		try {
			db.connectDB();
			Statement statement = db.connection.createStatement();
			String sql = "UPDATE bank.accounts SET balance='" + formattedBalance + "' WHERE username='"+ user.getUsername() + "'";
			statement.executeUpdate(sql);
			db.connection.commit();
			statement.close();
			db.closeDB();
			user.setBalance(formattedBalance);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	// SQL method for checking if the table exists and if not create it (not used
	// anymore good for initializing a table)
	public static void checkTable() {
		try {
			db.connectDB();
			Statement statement = db.connection.createStatement();
			// connection.createStatement().execute("CREATE SCHEMA accounts");
			// create table if it doesn't exist
			String sql = "CREATE TABLE IF NOT EXISTS bank.accounts" 
					   + "(USERNAME     varchar(255)    NOT NULL, "
					   + " BALANCE		varchar(255)	 NOT NULL," 
					   + " ID  SERIAL PRIMARY KEY)";
			statement.executeUpdate(sql);
			statement.close();
			db.closeDB();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}
