package wiedman.tony.bank;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DecimalFormat;

public class User {
	// Private User variables
	private int id;
	private String name;
	private String username;
	private String password;
	
	
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
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	private double balance;
	
	
	
	
	public User() {
		super();
		this.username = "";
		this.password = "";
		this.balance = 0.0;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.balance = 0.0;
	}
	
}


	
	
	
	
	
	
	
	
	
	
		