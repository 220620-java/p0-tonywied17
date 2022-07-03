package tony.bank.service;

import tony.bank.data.access.*;
import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.structure.List;


public class UserServiceExec implements UserService {
	
	//private User DAO userDao = new UserPostgres;
	private AccountDAO accountDao = new AccountPostgres();
	
	@Override
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User logIn(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> viewAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User openAccount(Account account, User user) {
		// TODO Auto-generated method stub
		
		accountDao.create(account, user);
		return null;
	}

}
