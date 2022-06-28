package wiedman.tony.models;

import java.text.DecimalFormat;
import wiedman.tony.service.SQL;

public class User {
	// Private User Object Variables
	private int id;
	private String name;
	private String username;
	private String password;
	private double balance;
	private boolean failed = false;
	private boolean loggedin = false;
	
	
	// Static SQL Object for calling methods from the SQL service.
	static SQL sql = new SQL();

	
	// Pass Main.user values (username and password) to the SQL service for a credential check 
	// and if  passed reassign Main.user's (static user) variables with the DB values
	public void userLogin(User user) {
		user.setFailed(failed);
		sql.selectUser(user);
	}
	
	// Make a deposit
	public void makeDeposit(User user, double amount) {
		double adjustedBalance = user.getBalance() + amount;
		sql.updateFunds(user, adjustedBalance);
	}
	
	//Make a withdrawal
	public void makeWithdraw(User user, double amount) {
		if (amount > user.getBalance()) {
			System.out.println("************Insufficient Funds*************");
		} else {
			double adjustedBalance = getBalance() - amount;
			sql.updateFunds(user, adjustedBalance);
		}
	}
	
	//Pass the Main.user values to the SQL service to store them in the database (create an account)
	public void createAccount(User user) {
		sql.insertUser(user);
	}
	
	// Default no args constructor for instantiating the static Main.user
	public User(){
		
	}

	
	//Getters and Setters
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

	//Format the balance to 2 decimal places
	public double getBalance() {
		DecimalFormat decim = new DecimalFormat("#.00");
		double roundedAmount = Double.parseDouble(decim.format(balance));
		return roundedAmount;
	}

	public void setBalance(double balance) {
		this.balance = balance;

	}
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

}
