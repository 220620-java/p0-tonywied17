package tony.bank.data.interfaces;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.structures.List;

/*
 * 
 * USER INTERFACE (ABSTRACT CLASS)
 * 
 * USER DATABASE FUNCTIONS DEFINED HERE WITH NO BODIES
 * 
 */


public interface UserDAO {

	// USED FOR LOGGING IN/CHECKING USERS CREDENTIALS
	public User findByUsername(String username);

	// CREATE A NEW USER ACCOUNT
	public User create(User user) throws UsernameAlreadyExistsException;

	// LIST ALL THE BANK ACCONTS FOR THE CURRENT USER
	List<Account> findAllAccounts(User user);


}
