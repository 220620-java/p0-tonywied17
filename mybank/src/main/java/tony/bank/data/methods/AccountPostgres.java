package tony.bank.data.methods;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tony.bank.app.model.*;
import tony.bank.data.connect.*;
import tony.bank.data.interfaces.AccountDAO;
import tony.bank.service.interfaces.AccountService;
import tony.bank.service.methods.AccountServiceExec;

public class AccountPostgres implements AccountDAO {
	private static AccountService accountService = new AccountServiceExec();
	private ConnectDB connUtil = ConnectDB.getConnectionDB();

	@Override
	public Account create(Account account, User user, double balance) {

		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "insert into bank3.account" + "(id, owner_id, balance) " + "values (default, ?, ?)";

			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setInt(1, user.getId());
			stmt.setDouble(2, balance);

			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				account.setId(resultSet.getInt("id"));
				account.setBalance(balance);
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

		try (Connection conn = connUtil.getConnection()) {

			String sql = "SELECT * from bank3.account WHERE owner_id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId()); 

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {

				double balanceDB = resultSet.getDouble("balance");
				int idDB = resultSet.getInt("id");

				account.setBalance(balanceDB);
				account.setId(idDB);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public Account updateBalance(Account account, double amount, String type, double transaction) {
		// TODO Auto-generated method stub

		try (Connection conn = connUtil.getConnection()) {

			String balanceQuery = "UPDATE bank3.account SET balance='" + amount + "' WHERE id='" + account.getId()
					+ "'";

			String transactionQuery = "INSERT INTO bank3.transactions (id, account_id, type, value, balance)"
					+ "VALUES (default, '" + account.getId() + "', '" + type + "', '" + transaction + "', '" + amount
					+ "');";

			Statement statement = conn.createStatement();
			statement.executeUpdate(balanceQuery);
			statement.executeUpdate(transactionQuery);
			statement.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public void printTrans(Account account) {
		// TODO Auto-generated method stub
		try (Connection conn = connUtil.getConnection()) {
			// set up the SQL statement that we want to execute
			String sql = "SELECT * FROM bank3.transactions WHERE account_id=?";

			// set up that statement with the database
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, account.getId());
			// execute the statement
			ResultSet resultSet = stmt.executeQuery();

			// process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				// int accountId = resultSet.getInt("account_id");
				String type = resultSet.getString("type");
				double value = resultSet.getDouble("value");
				double balance = resultSet.getDouble("balance");

				System.out.println("🆔 𝗧𝗿𝗮𝗻𝘀𝗮𝗰𝘁𝗶𝗼𝗻 𝗜𝗗 " + id + "\n                                                                                                                       \n🪢 𝗧𝗿𝗮𝗻𝘀𝗮𝗰𝘁𝗶𝗼𝗻 𝗠𝗲𝘁𝗵𝗼𝗱 " + type
						+ "\n💳 𝗧𝗿𝗮𝗻𝘀𝗮𝗰𝘁𝗶𝗼𝗻 𝗔𝗺𝗼𝘂𝗻𝘁 " + accountService.convertCurrency(value) + "\n💰 𝗔𝘃𝗮𝗶𝗹𝗮𝗯𝗹𝗲 𝗕𝗮𝗹𝗮𝗻𝗰𝗲 "
						+ accountService.convertCurrency(balance));
				System.out.println("\n");
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
