package tony.bank.data.interfaces;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.structures.List;

public interface AccountDAO {

	Account create(Account account, User user, double balance);

	Account get(Account account, User user);

	void printTrans(Account account);

	Account updateBalance(Account account, double adjustedBalance, String type, double transAmount);


}
