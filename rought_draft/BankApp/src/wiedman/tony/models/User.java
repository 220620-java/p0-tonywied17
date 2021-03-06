package wiedman.tony.models;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import wiedman.tony.sql.Queries;

public class User {
	
	// USER PROPERTIES
	private int id;
	private String name, username, password, accountNumber, accountType;
	private boolean failed = false, loggedin = false;
	private double balance;
	Locale locale = new Locale("en", "US"); 

	
	
	// LOGIN METHOD
	public void userLogin(User user) throws Exception {
		
		Queries.selectUser(user);
	}
	
	
	
	// DEPOSIT BALANCE METHOD
	public void makeDeposit(User user, double amount) throws Exception {
		double adjustedBalance = user.getBalance() + amount;
		
			Queries.updateFunds(user, adjustedBalance);
	}
	
	
	
	// WITHDRAWAL METHOD
	public void makeWithdraw(User user, double amount) throws Exception {
		
		if (amount > user.getBalance()) {
			System.out.println("Ｉｎｓｕｆｆｉｃｉｅｎｔ Ｆｕｎｄｓ！");
		} else {
			double adjustedBalance = getBalance() - amount;
			
				Queries.updateFunds(user, adjustedBalance);
		}
	}
	
	
	
	// CREATE BANK ACCOUNT METHOD
	public void createAccount(User user) throws Exception {
		
		Queries.insertUser(user);
	}
	
	
	
	// DEFAULT NO-ARGS USER METHOD
	public User(){
		
	}

	
	
	// -- GETTERS AND SETTERS --

	
	// BALANCE
	public double getBalance() {
		
		return balance;
	
	}

	// CONVERT BALANCE TO LOCALE CURRENCY
	public String convertCurrency(double d) {
		BigDecimal toDecimal = new BigDecimal(d);
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		String currencyBalance = currencyFormatter.format(toDecimal);
		
		return currencyBalance;
	}

	public void setBalance(double balance) {
		
		this.balance = balance;
		
	}
	
	
	
	// USER ID
	public int getId() {
		
		return id;
		
	}
	
	public void setId(int id) {
		this.id = id;
	}

	
	
	// NAME
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	// USERNAME
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	// PASSWORD
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	// FAILED
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	
	
	
	// LOGGED IN
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	
	
	// ACCOUNT NUMBER
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	
	
	// ACCOUNT TYPE
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
	
}
