package tony.bank.data.access;

import tony.bank.app.model.User;
import tony.bank.data.structure.List;

public interface UserDAO {
	
	public User findByUsername(String username);

	public User create(User t);

	public User findById(int id);

}
