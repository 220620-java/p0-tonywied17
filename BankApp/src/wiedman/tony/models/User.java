package wiedman.tony.models;

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
		DecimalFormat decim = new DecimalFormat("#.00");
		double roundedAmount = Double.parseDouble(decim.format(balance));
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
