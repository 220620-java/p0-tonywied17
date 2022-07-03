package tony.bank.app.model;

import java.util.Objects;

import org.postgresql.util.GT;

import tony.bank.data.structure.ArrayList;
import tony.bank.data.structure.List;


public class Account {
	private int id;
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
		return  id == other.id && balance == other.balance;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + "]";
	}


	
}
