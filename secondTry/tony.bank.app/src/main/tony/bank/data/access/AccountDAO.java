package tony.bank.data.access;

import tony.bank.data.structure.List;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;

public interface AccountDAO {
	

	Account create(Account account, User user);

	List<Account> findAll();
	
}
