package wiedman.tony.models;

import java.text.DecimalFormat;
import wiedman.tony.service.SQL;

public class User {
	// Private User variables
	private int id;
	private String name;
	private String username;
	private String password;
	private double balance;
	private boolean failed;
	
	// SQL object for calling methods from the SQL service
	static SQL sql = new SQL();


	public void userLogin(User user) {
		sql.selectUser(user);
	}
	public void makeDeposit(User user, double amount) {
		sql.setDeposit(amount);
	}
	public void createAccount(User user) {
		sql.insertUser(user);
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
	public boolean isFailed() {
		return failed;
	}
	public boolean setFailed(boolean failed) {
		this.failed = failed;
		return failed;
	}

}
