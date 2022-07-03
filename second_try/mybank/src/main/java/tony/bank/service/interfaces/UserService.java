package tony.bank.service.interfaces;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.User;

public interface UserService {

	User logIn(String username, String password);

	User registerUser(User user) throws UsernameAlreadyExistsException;

}
