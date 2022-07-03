package tony.bank.data.access;

import tony.bank.app.model.User;

public interface UserDAO {
	
	public User findByUsername(String username);

	public User create(User user);

	public User findById(int id);

}
