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

	// Login and get account balance
	public User(String username, String password) {

		int returnVal = sql.loginUser(username, password);

		if(returnVal == 0) {
			System.out.println("Account not found!");
		}else {
			System.out.println("Account Balance:");
			sql.selectBalance("BALANCE", returnVal);
			this.setBalance(returnVal, returnVal);
		}
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
