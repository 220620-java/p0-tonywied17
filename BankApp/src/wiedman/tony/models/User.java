package wiedman.tony.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

import wiedman.tony.service.SQL;

public class User {
	// Private User variables
	private int id;
	private String name;
	private String username;
	private String password;
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";
	
	static SQL sql = new SQL();
	
	static Scanner scanner = new Scanner(System.in);
	static DecimalFormat deciFormat = new DecimalFormat();

	// Account Creation Constructor
	public User(String name, String username, String password, String confirm) {
		sql.checkTable();

		// User validation logic
		if (name != null) {
			
			//set
			this.setName(name);
		} else {
			System.out.println("You must enter your full name!");
		}

		if (username != null) {
			
			//set
			this.setUsername(username);
		} else {
			System.out.println("You must enter a username!");
		}

		if (password != null) {
			
			//set
			this.setPassword(password);
			
		} else if (password.equals(confirm)) {
			System.out.println("Passwords do not match!");
		} else {
			System.out.println("You must enter a password!");
		}
		
		sql.insertValue(name, username, password, balance);
	}

	// Login constructor
	public User(String username, String password) {

		sql.checkTable();

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

				int idDB = rs.getInt("ID");
				double balanceDB = rs.getDouble("BALANCE");
				String nameDB = rs.getString("NAME");
				String usernameDB = rs.getString("USERNAME");
				String passwordDB = rs.getString("PASSWORD");
				String compareDB = usernameDB + ":" + passwordDB;

				if (compareDB.equals(compareInp)) {
					this.setId(idDB);
					this.setName(nameDB);
					this.setUsername(usernameDB);
					this.setPassword(passwordDB);
					this.setBalance(balanceDB, idDB);

					boolean userContinue = true;

					while (userContinue) {

						System.out.println("/-------------------------------------------/" + "\n"
								+ "* Big Bucks Bank - Account# " + this.getId() + " - " + this.getUsername() + "\n"
								+ "/-------------------------------------------/" + "\n" + "Balance: $"
								+ this.getBalance() + "\n\n" + "1.] Make Deposit" + "\n" + "2.] Make Withdrawal" + "\n"
								+ "\n" + "3.] Logout" + "\n"
								+ "/-------------------------------------------/\nType an option:");
						String input = scanner.nextLine();

						switch (input) {
						case "1":
							// make a deposit
							System.out.println("Enter Amount:");
							double depositAmount = scanner.nextDouble();

							this.setBalance(getBalance() + depositAmount, idDB);
							break;
						case "2":
							// make a withdrawal
							System.out.println("Enter Amount:");
							double withdrawAmount = scanner.nextDouble();
							if (withdrawAmount > this.getBalance()) {
								System.out.println("************Insufficient Funds*************");
							} else {
								this.setBalance(getBalance() - withdrawAmount, idDB);
							}
							break;
						case "3":
							userContinue = false;

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

		this.username = username;
		this.password = password;

		this.balance = 25.00;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		double roundedAmount = Math.round(balance * 100.0) / 100.0;
		return roundedAmount;
	}

	public void setBalance(double balance, int id) {

		this.balance = balance;
		sql.updateValue("BALANCE", balance, id);

	}

	private double balance;

}
