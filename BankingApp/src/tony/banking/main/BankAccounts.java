package tony.banking.main;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;

public class BankAccounts implements Serializable {

	private int id;
	private String fullname;
	private String username;
	private String password;
	private int accountnumber;
	private String accountbalance;
	
	
	
	public static int count = 0;
	public static boolean randomBoolean;
	
	public void CreateAccount(String fullname, String username, String password) {
		ArrayList<String> accounts = new ArrayList<String>();
		
		System.out.println("Creating account for " + fullname + "(" + username + ")");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccountbalance() {
		return accountbalance;
	}

	public void setAccountbalance(String accountbalance) {
		this.accountbalance = accountbalance;
	}

}
