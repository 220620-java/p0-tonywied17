package tony.bank.data.interfaces;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountDAO {

	Account get(Account account, User user);

	void printTrans(Account account);

	Account create(Account account, User user, double balance, String accountType);

	Account updateBalance(Account account, double adjustedBalance);

	public Account insertTrans(Account account, double adjustedBalance, String type, double transAmount);
}
