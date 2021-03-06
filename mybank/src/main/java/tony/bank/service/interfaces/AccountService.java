package tony.bank.service.interfaces;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;

/*
 * 
 * ACCOUNT SERVICE INTERFACE (ABSTRACT CLASS)
 * 
 * ACCOUNT SERVICE FUNCTIONS DEFINED HERE WITH NO BODIES
 * 
 * This user service acts as a intermediary between the account model and the account data access object.
 * 
 */

public interface AccountService {
	
	public Account openAccount(Account account, User user, double deposit, String accountType);

	String convertCurrency(double d);

	Account viewTransactions(Account account);

	Account makeWithdraw(Account account, double amount);

	Account makeDeposit(Account account, double amount);

	
}
