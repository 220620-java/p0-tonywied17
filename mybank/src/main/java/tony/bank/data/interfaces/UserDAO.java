package tony.bank.data.interfaces;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.structures.List;

public interface UserDAO {

	public User findByUsername(String username);

	public User create(User user) throws UsernameAlreadyExistsException;

	List<Account> findAllAccounts(User user);


}
