package tony.bank.service.methods;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.interfaces.*;
import tony.bank.data.methods.UserPostgres;
import tony.bank.data.structures.List;
import tony.bank.service.interfaces.UserService;

public class UserServiceExec implements UserService {

	// private User DAO userDao = new UserPostgres;
	private UserDAO userDao = new UserPostgres();

	@Override
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		user = userDao.create(user);
		if (user == null) {
			throw new UsernameAlreadyExistsException();
		}
		return user;

	}

	@Override
	public User logIn(String username, String password) {
		// TODO Auto-generated method stub

		User user = userDao.findByUsername(username);

		if (user != null && (password != null && password.equals(user.getPassword()))) {
			return user;
		} else {
			return null;
		}

	}
	
	@Override
	public List<Account> getAccountInfo(User user) {
		return userDao.findAllAccounts(user);
	}
}
