package tony.bank.data.interfaces;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;


/*
 * 
 * ACCOUNT INTERFACE (ABSTRACT CLASS)
 * 
 * ACCOUNT DATABASE FUNCTIONS DEFINED HERE WITH NO BODIES
 * 
 * An abstract class used to define functions and their arguments to be later implemented in the PostgreSQL methods
 * 
 */



public interface AccountDAO {

	// GET USER ACCOUNT INFORMATION
	Account get(Account account, User user);

	// PRINT TRANSACTIONS FOR SELECTED USER ACCOUNT
	void printTrans(Account account);

	// LET THE USER CREATE/ADD A NEW ACCOUNT
	Account create(Account account, User user, double balance, String accountType);

	// LET THE USE UPDATE THEIR ACCOUNT BALANCE VIA DEPOSIT OR WITHDRAWAL
	Account updateBalance(Account account, double adjustedBalance);

	// INSERT TRANSACTION RECORD INTO DATABASE
	public Account insertTrans(Account account, double adjustedBalance, String type, double transAmount);
}
