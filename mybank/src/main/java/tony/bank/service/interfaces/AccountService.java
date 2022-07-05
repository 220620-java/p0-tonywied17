package tony.bank.service.interfaces;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountService {
	
	public Account openAccount(Account account, User user, double deposit);

	String convertCurrency(double d);

	Account viewTransactions(Account account);

	Account makeWithdraw(Account account, double amount);

	Account makeDeposit(Account account, double amount);

	
}
