package tony.bank.service;

import tony.bank.data.access.*;
import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.User;


public class UserServiceExec {
	
	//private User DAO userDao = new UserPostgres;
	private UserDAO userDao = new UserPostgres();
	
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		user = userDao.create(user);
		if (user == null) {
			throw new UsernameAlreadyExistsException();
		}
		return user;

	}

	public User logIn(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(username);
		if (user != null && (password!=null && password.equals(user.getPassword()))) {
			return user;
		} else {
			return null;
		}

	}


	

}