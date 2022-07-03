package tony.bank.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import tony.bank.app.model.*;
import tony.bank.data.structure.*;
import tony.bank.db.*;

public class AccountPostgres implements AccountDAO{
	
	private ConnectDB connUtil = ConnectDB.getConnectionDB();
	
	@Override
	public Account create(Account account, User user, double balance) {

		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into bank3.account"
					+ "(id, owner_id, balance) "
					+ "values (default, ?, ?)";

			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setInt(1, user.getId());
			stmt.setDouble(2, balance);
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected==1) {
				account.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;

	}


	
	@Override
	public List<Account> findAll() {
		
		List<Account> allAccounts = new ArrayList<>();
		
		try (Connection conn = connUtil.getConnection()) {
			// set up the SQL statement that we want to execute
			String sql = """
					
					SELECT * 
					
					from bank3.account WHERE owner_id = ?;
					
					""";

			// set up that statement with the database
			Statement stmt = conn.createStatement();

			// execute the statement
			ResultSet resultSet = stmt.executeQuery(sql);

			// process the result set
			while (resultSet.next()) {
				int accountNumber = resultSet.getInt("id");
				double balance = resultSet.getDouble("balance");


				Account account = new Account(accountNumber, balance);
				account.setId(accountNumber);

				allAccounts.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return allAccounts;
	}

	
	

}
