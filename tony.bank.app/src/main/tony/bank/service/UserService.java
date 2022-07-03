package tony.bank.service;
import tony.bank.data.structure.List;

import tony.bank.app.model.User;
import tony.bank.app.model.Account;
import tony.bank.app.exceptions.*;
import tony.bank.data.access.*;
import tony.bank.data.structure.List;

public interface UserService {

	public User registerUser(User user) throws UsernameAlreadyExistsException;
	
	public User logIn(String username, String password);
	
	public List<Account> viewAllAccounts();
	
	public User openAccount(Account account, User user);
	
	
}
