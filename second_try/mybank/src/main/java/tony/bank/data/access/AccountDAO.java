package tony.bank.data.access;

import tony.bank.data.structure.List;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountDAO {
	

	Account create(Account account, User user, double balance);

	Account get(Account account, User user);

	Account updateBalance(Account account, double amount);
}
