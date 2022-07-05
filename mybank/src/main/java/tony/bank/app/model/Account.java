package tony.bank.app.model;

import tony.bank.service.interfaces.AccountService;
import tony.bank.service.methods.AccountServiceExec;

public class Account {
	// Account ID
	private int id;
	// Account Balance
	private double balance;
	

	// No args constructor for account object
	public Account() {
		super();

		this.id = 0;
		this.balance = 0;
	}

	public Account(int id, double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

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
		return    "\n                                                                                                                       \n"
				+ " 𝗕𝗮𝗹𝗮𝗻𝗰𝗲 💰" + accountService.convertCurrency(balance)
				+ "\n                                                                                                                       \n"
				+ " 𝗜𝗗:  ["+id+"]";
	}

}
