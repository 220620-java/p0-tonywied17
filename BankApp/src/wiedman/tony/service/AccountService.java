package wiedman.tony.service;

import wiedman.tony.models.User;
import wiedman.tony.exception.*;

public interface AccountService {
	public void createAccount(User user) throws UsernameTakenException;
	public User logIn(String username, String password);
	public void depositMoney(double amount, int id);
}
