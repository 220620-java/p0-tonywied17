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
	private String accountNumber;
	private String accountType;
	private boolean failed = false;
	private boolean loggedin = false;

	
	
	// Pass Main.user values (user name and password) to the SQL service for a credential check 
	// and if  passed reassign Main.user's (static user) variables with the DB values
	public void userLogin(User user) throws Exception {
		
			SQL.selectUser(user);
		
	}
	
	
	
	// Make a deposit
	public void makeDeposit(User user, double amount) throws Exception {
		double adjustedBalance = user.getBalance() + amount;
		
			SQL.updateFunds(user, adjustedBalance);
	}
	
	
	
	//Make a withdrawal
	public void makeWithdraw(User user, double amount) throws Exception {
		
		if (amount > user.getBalance()) {
			
			System.out.println("Insufficient Funds! No Overdrafting Allowed!");
			
		} else {
			
			double adjustedBalance = getBalance() - amount;
			
				SQL.updateFunds(user, adjustedBalance);
			
		}
	}
	
	
	
	//Pass the Main.user values to the SQL service to store them in the database (create an account)
	public void createAccount(User user) throws Exception {
		
				SQL.insertUser(user);
	}
	
	
	
	// Default no argument constructor for instantiating the static Main.user
	public User(){
		
	}

	
	
	//Getters and Setters
	public double getBalance() {
		
		DecimalFormat decim = new DecimalFormat("#.00");
		double roundedAmount = Double.parseDouble(decim.format(balance));
		
		return roundedAmount;
		
	}

	
	
	public void setBalance(double balance) {
		
		this.balance = balance;
		
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

	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
	
}
