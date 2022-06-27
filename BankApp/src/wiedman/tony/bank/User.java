package wiedman.tony.bank;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

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

	// overloaded constructor: a constructor with parameters
	public User(String name, String username, String password) {
		if (name != null) {
			this.name = name;
		} else {
			System.out.println("You must enter your full name!");
		}
		this.username = username;
		this.password = password;
		this.setBalance(25.00);
	}

	// login user object
	public User(String username2, String password2) {
		// TODO Auto-generated constructor stub
		this.username = username2;
		this.password = password2;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double amount) {
		this.balance = amount;
	}

	public void withdrawBalance(double amount) {

		if (amount > this.balance) {
			System.out.println("You can not overdraft!");
			System.out.println("Your current balance: " + this.getBalance());
		} else {
			this.balance = this.balance - amount;
			System.out.println("Your current balance: " + this.getBalance());
		}

	}

	public void depositBalance(double amount) {
		this.balance = this.balance + amount;
		System.out.println("Your current balance: " + this.getBalance());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", balance="
				+ balance + "]";
	}

}
