package tony.bank.service.interfaces;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.structures.List;


/*
 * 
 * USER SERVICE INTERFACE (ABSTRACT CLASS)
 * 
 * USER SERVICE FUNCTIONS DEFINED HERE WITH NO BODIES
 * 
 */


public interface UserService {

	User logIn(String username, String password);

	User registerUser(User user) throws UsernameAlreadyExistsException;

	List<Account> getAccountInfo(User user);

}
