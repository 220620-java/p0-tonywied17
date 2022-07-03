package tony.bank.data.access;

import tony.bank.data.structure.List;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountDAO {
	

	List<Account> findAll();

	Account create(Account account, User user, double balance);
	
}
