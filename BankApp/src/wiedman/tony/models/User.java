package wiedman.tony.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

import wiedman.tony.bank.Main;
import wiedman.tony.service.SQL;

public class User {
	// Private User variables
	private int id;
	private String name;
	private String username;
	private String password;
	private String amount;
	
	

	static SQL sql = new SQL();
	
	static Scanner scanner = new Scanner(System.in);
	static DecimalFormat deciFormat = new DecimalFormat();

	// Account Creation Constructor
	public User(String name, String username, String password) {
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
			
		} else {
			System.out.println("You must enter a password!");
		}
		
	}



	public void userLogin(User user) {
		sql.loginUser(Main.user);
	}
	public void makeDeposit(User user, double amount) {
		sql.setDeposit(amount);
	}
	public void createAccount(User user) {
		System.out.println("not yet implemented");
	}
	
	public User(){
		
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

	public void setBalance(double balance) {
		this.balance = balance;

	}
	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}

	private double balance;

}
