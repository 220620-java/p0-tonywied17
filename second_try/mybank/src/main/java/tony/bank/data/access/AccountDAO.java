package tony.bank.data.access;

import tony.bank.data.structure.List;

import org.postgresql.util.GT;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountDAO {
	

	Account create(Account account, User user, double balance);

	Account get(Account account, User user);


	void printTrans(Account account);

	Account updateBalance(Account account, double depositBalance, String string, double amount);


}
