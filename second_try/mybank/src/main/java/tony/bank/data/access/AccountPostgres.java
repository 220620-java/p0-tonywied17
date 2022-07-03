package tony.bank.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
	public Account get(Account account, User user) {

		// TODO Auto-generated method stub
				
				// try-with-resources: sets up closing for closeable resources
				try (Connection conn = connUtil.getConnection()) {
					// set up the SQL statement that we want to execute
					String sql = "SELECT * from bank3.account WHERE owner_id = ?;";
					PreparedStatement stmt = conn.prepareStatement(sql);
					System.out.println("ID: " + user.getId());
					stmt.setInt(1, user.getId()); // parameter indexes start at 1 (the first ?)

					// execute the statement
					ResultSet resultSet = stmt.executeQuery();

					// process the result set
					if (resultSet.next()) {
						
						double balanceDB = resultSet.getDouble("balance");
						int idDB = resultSet.getInt("id");
						
						System.out.println("bal: " + balanceDB + ", id: " + idDB);
						//account.setBalance(28);
						account.setBalance(balanceDB);
						account.setId(idDB);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return account;
	}


	

	
	

}
