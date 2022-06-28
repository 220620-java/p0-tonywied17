package wiedman.tony.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import wiedman.tony.bank.Main;
import wiedman.tony.models.User;

public class SQL {
	static DecimalFormat deciFormat = new DecimalFormat();
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";

	Connection c = null;
	Statement statement = null;

	public void checkTable() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);

			statement = c.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS ACCOUNT" + "(NAME           varchar(255)    NOT NULL, "
					+ " USERNAME       varchar(255)    NOT NULL, " + " PASSWORD       varchar(255)    NOT NULL, "
					+ " BALANCE		 varchar(255)	 NOT NULL," + " ID  SERIAL PRIMARY KEY)";
			statement.executeUpdate(sql);
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public User loginUser(User user) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
			statement = c.createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM ACCOUNT WHERE USERNAME='" + Main.user.getUsername() + "'");

			while (result.next()) {
				String nameDB = result.getString("NAME");
				String usernameDB = result.getString("USERNAME");
				String passwordDB = result.getString("PASSWORD");
				double balanceDB = result.getDouble("BALANCE");
				int idDB = result.getInt("ID");

				if (Main.user.getPassword().equals(passwordDB)) {
					System.out.println("Account Found!");
					Main.user.setName(nameDB);
					Main.user.setUsername(usernameDB);
					Main.user.setPassword(passwordDB);
					Main.user.setBalance(balanceDB);
					Main.user.setId(idDB);
				} else {
					System.out.println("No account found with those credentials");
					Main.user.setName(null);
					Main.user.setUsername(null);
					Main.user.setPassword(null);
				}

			}
			result.close();
			statement.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return Main.user;

	}

	public void setDeposit(double amount) {
		
		double newBalance = Main.user.getBalance() + amount;
		int id = Main.user.getId();
		
		try {
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);

	         statement = c.createStatement();
	         String sql = "UPDATE ACCOUNT set BALANCE = "+amount+" where ID="+id+";";
	         statement.executeUpdate(sql);
	         c.commit();
	         statement.close();
	         c.close();
	         System.out.println("Deposit of: $ " + amount + " has been received.");
	         Main.user.setBalance(newBalance);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}
