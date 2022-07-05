package tony.bank.data.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.User;
import tony.bank.data.connect.*;
import tony.bank.data.interfaces.UserDAO;

public class UserPostgres implements UserDAO {
	private ConnectDB connUtil = ConnectDB.getConnectionDB();

	@Override
	public User create(User user) throws UsernameAlreadyExistsException {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "insert into bank3.users" + "(id, username, password, name, phone, email) " + "values (default, ?, ?, ?, ?, ?)";

			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getEmail());

			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				user.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}


	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = null;

		// try-with-resources: sets up closing for closeable resources
		try (Connection conn = connUtil.getConnection()) {
			// set up the SQL statement that we want to execute
			String sql = """

					SELECT * from bank3.users WHERE username = ?;

					""";
			// set up that statement with the database
			// preparedstatement is pre-processed to prevent sql injection
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username); // parameter indexes start at 1 (the first ?)

			// execute the statement
			ResultSet resultSet = stmt.executeQuery();

			// process the result set
			if (resultSet.next()) {
				String usernameDB = resultSet.getString("username");
				String passwordDB = resultSet.getString("password");
				String nameDB = resultSet.getString("name");
				String phoneDB = resultSet.getString("phone");
				String emailDB = resultSet.getString("email");
				int idDB = resultSet.getInt("id");

				user = new User(idDB, usernameDB, passwordDB, nameDB, phoneDB, emailDB);
				user.setLoggedIn(true);
			} else {
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

}
