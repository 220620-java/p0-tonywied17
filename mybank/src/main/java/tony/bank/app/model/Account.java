package tony.bank.app.model;

import tony.bank.data.structures.ArrayList;
import tony.bank.data.structures.List;
import tony.bank.service.interfaces.AccountService;
import tony.bank.service.methods.AccountServiceExec;


/*
 * ACCOUNT MODEL
 * 
 * FIELDS: ID, BALANCE, ACCOUNT TYPE
 * 
 * The account model acts as a blueprint for what will be stored inside an ArrayList in the User model
 * 
 */

public class Account {
	
	/*
	 * ACCOUNT MODEL PROPERTIES
	 */
	private int id;
	private double balance;
	private String accountType;



	// NO-ARGS CONSTRUCTOR FOR ACCOUNT MODEL
	public Account() {
		super();

		this.id = 0;
		this.balance = 0;
	}

	// OVERLOADED ACCOUNT MODEL CONSTRUCTOR - 
	// USED FOR INSTANIATING AN ACCOUNT MODEL WITH ALL VALUES APPLIED TO THE CLASS PROPERTIES
	public Account(int id, double balance, String accountType) {
		super();
		this.id = id;
		this.balance = balance;
		this.accountType = accountType;
	}

	
	/*
	 * Account getters and setters (encapsulation)
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	/*
	 * CUSTOM DATA STRUCTURE ARRAY(
	 * 
	 * EQUALS - A method used to compare account properties elsewhere
	 * 
	 * TO STRING - Used to display an output of the account ArrayList
	 * 
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return id == other.id && balance == other.balance;
	}
	
	private static AccountService accountService = new AccountServiceExec();
	
	
	@Override
	public String toString() {
		return  "🆔 𝗔𝗰𝗰𝗼𝘂𝗻𝘁 𝗜𝗗:  [" + id + "]  	💰 𝗕𝗮𝗹𝗮𝗻𝗰𝗲: " + accountService.convertCurrency(balance) + "\n\n📒 𝗔𝗰𝗰𝗼𝘂𝗻𝘁 𝗧𝘆𝗽𝗲: " + accountType
				+ "\n                                                                                                                                      \n";
	}

}
