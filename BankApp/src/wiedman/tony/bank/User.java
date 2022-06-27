package wiedman.tony.bank;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Scanner;
import java.sql.*;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String username;
	private String password;
	private double balance;
	static Scanner scanner = new Scanner(System.in);
	public static int count = 0;
	

	{
		this.id = ++count;
		// this.id = count++;
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

	// create account constructor
	public User(String name, String username, String password, String confirmPassword) {
		if (name != null) {
			this.name = name;
		} else {
			System.out.println("You must enter your full name!");
		}
		
		if (username != null) {
			this.username = username;
		} else {
			System.out.println("You must enter a username!");
		}
		
		if (password != null) {
			this.password = password;
		}else if(password != confirmPassword) {
			System.out.println("Passwords do not match!");
		} else {
			System.out.println("You must enter a password!");
		}
		
		// initial balance
		this.setBalance(25.00);
	}

	
	
	// login constructor
	public User(String username2, String password2) {
		// TODO Auto-generated constructor stub
		this.username = username2;
		this.password = password2;
		
		//for example purposes for now
		this.balance = 25.00;
	}

	
	//getting the balance
	public double getBalance() {
		double roundedAmount = Math.round(balance * 100.0) / 100.0;
		return roundedAmount;
	}

	
	//Setting the balance
	public void setBalance(double amount) {
		double roundedAmount = Math.round(amount * 100.0) / 100.0;
		this.balance = roundedAmount;
	}

	public void withdrawBalance(double amount) {

		if (amount > this.balance) {
			System.out.println("/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Account - "
					+ username + "\n" + "/-------------------------------------------/" + "\n"
					+ "Overdraft not enabled.\n\n" + "Withdrawal for '$" + amount + "' could not be approved." + "\n"
					+ "\n" + "/-------------------------------------------/\n");
		} else {

			// set the new balance
			this.setBalance(balance - amount);

			System.out.println("/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Account - "
					+ username + "\n" + "/-------------------------------------------/" + "\n" + "\n"
					+ "Withdrawal for '$" + amount + "' completed!" + "\n" + "\n"
					+ "/-------------------------------------------/\n");

			System.out.println("Your current balance: $" + this.getBalance());
		}

	}

	public void depositBalance(double amount) {

		// set the new balance
		this.setBalance(balance + amount);

		System.out.println("/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Account - "
				+ username + "\n" + "/-------------------------------------------/" + "\n" + "\n" + "Deposit for '$"
				+ amount + "' completed!" + "\n" + "\n" + "/-------------------------------------------/\n");
	}

	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", balance="
				+ balance + "]";
	}

}
