package tony.bank.data.interfaces;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountDAO {

	Account get(Account account, User user);

	void printTrans(Account account);

	Account updateBalance(Account account, double adjustedBalance, String type, double transAmount);

	Account create(Account account, User user, double balance, String accountType);


}
