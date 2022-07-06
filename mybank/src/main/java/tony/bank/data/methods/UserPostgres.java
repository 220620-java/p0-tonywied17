package tony.bank.data.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.connect.*;
import tony.bank.data.interfaces.UserDAO;
import tony.bank.data.structures.ArrayList;
import tony.bank.data.structures.List;

/*
 * USER INTERFACE IMPLEMENTED HERE
 * 
 * PRE-DEFINED FUNCTIONS WRITTEN AND OVERRIDDEN
 * 
 */


public class UserPostgres implements UserDAO {
	private ConnectDB connUtil = ConnectDB.getConnectionDB();

	@Override
	public User create(User user) throws UsernameAlreadyExistsException {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "insert into bank3.users" + "(id, username, password, fullname, phone, email) "
					+ "values (default, ?, ?, ?, ?, ?)";

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
		List<Account> allAccounts = new ArrayList<>();
		// TODO Auto-generated method stub
		User user = null;

		// try-with-resources: sets up closing for closeable resources
		try (Connection conn = connUtil.getConnection()) {
			// set up the SQL statement that we want to execute
			String sql = """

					SELECT users.id as user_id, users.username , users.password , users.fullname , users.phone , users.email , account.id as account_number, account.balance, account.account_type
					FROM bank3.users
					LEFT JOIN bank3.account ON users.id  = account.owner_id
					where users.username = ?;

					""";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				String usernameDB = resultSet.getString("username"), passwordDB = resultSet.getString("password"),
						nameDB = resultSet.getString("fullname"), phoneDB = resultSet.getString("phone"),
						emailDB = resultSet.getString("email");
				int idDB = resultSet.getInt("user_id");

				user = new User(idDB, usernameDB, passwordDB, nameDB, phoneDB, emailDB);
				user.setLoggedIn(true);

				double balance = resultSet.getDouble("balance");
				int accountNumber = resultSet.getInt("account_number");
				String accountType = resultSet.getString("account_type");

				Account account = new Account(accountNumber, balance, accountType);

				allAccounts.add(account);
			} else {
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	@Override
	public List<Account> findAllAccounts(User user) {
		List<Account> allAccounts = new ArrayList<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = """

					SELECT users.id as user_id, users.username , users.password , users.fullname , users.phone , users.email , account.id as account_number, account.balance, account.account_type
					FROM bank3.Users
					LEFT JOIN bank3.Account ON users.id  = account.owner_id
					where users.username = ?;

					""";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				double balance = resultSet.getDouble("balance");
				int accountNumber = resultSet.getInt("account_number");
				String accountType = resultSet.getString("account_type");
				Account account = new Account(accountNumber, balance, accountType);

				allAccounts.add(account);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allAccounts;
	}

}
