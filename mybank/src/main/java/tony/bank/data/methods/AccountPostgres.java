package tony.bank.data.methods;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tony.bank.app.model.*;
import tony.bank.data.connect.*;
import tony.bank.data.interfaces.AccountDAO;
import tony.bank.data.structures.ArrayList;
import tony.bank.data.structures.List;
import tony.bank.service.interfaces.AccountService;
import tony.bank.service.methods.AccountServiceExec;

public class AccountPostgres implements AccountDAO {
	private static AccountService accountService = new AccountServiceExec();
	private ConnectDB connUtil = ConnectDB.getConnectionDB();

	@Override
	public Account create(Account account, User user, double balance, String accountType) {
		List<Account> allAccounts = new ArrayList<>();
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String sql = "insert into bank3.account" + "(id, owner_id, balance, account_type) "
					+ "values (default, ?, ?, ?)";

			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setInt(1, user.getId());
			stmt.setDouble(2, balance);
			stmt.setString(3, accountType);
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {

				conn.commit();

				Account account1 = new Account(resultSet.getInt("id"), balance, accountType);

				allAccounts.add(account1);
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

			String sql = "SELECT * FROM bank3.account where account.id = '?';";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, account.getId());

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
	public Account updateBalance(Account account, double adjustedBalance) {

		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);

			String balanceQuery = "UPDATE bank3.account SET balance=? WHERE id=?;";

			PreparedStatement balStmt = conn.prepareStatement(balanceQuery);

			balStmt.setDouble(1, adjustedBalance);
			balStmt.setInt(2, account.getId());

			balStmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;

	}

	public Account insertTrans(Account account, double adjustedBalance, String type, double transAmount) {

		try (Connection conn = connUtil.getConnection()) {
			

			String sql = "insert into bank3.transactions" + "(id, acc_type, amount, balance_after, account_id) "
					+ "values(default, ?, ?, ?, ?)";

				PreparedStatement stmt = conn.prepareStatement(sql);

				//System.out.println("values(default, '"+type+"', '"+transAmount+"', '"+adjustedBalance+"', '"++"')");
					stmt.setString(1, type);
					stmt.setDouble(2, transAmount);
					stmt.setDouble(3, adjustedBalance);
					stmt.setInt(4, account.getId());

				    stmt.executeUpdate();

				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return account;

	}

	@Override
	public void printTrans(Account account) {

		try (Connection conn = connUtil.getConnection()) {

			String sql = "SELECT * FROM bank3.transactions WHERE account_id = ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, account.getId());
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String type = resultSet.getString("acc_type");
				double value = resultSet.getDouble("amount");
				double balance = resultSet.getDouble("balance_after");

				System.out.println("\n🆔 𝗧𝗿𝗮𝗻𝘀𝗮𝗰𝘁𝗶𝗼𝗻 𝗜𝗗 " + id
						+ "\n                                                                                                                                      \n"
						+ "🪢 𝗧𝗿𝗮𝗻𝘀𝗮𝗰𝘁𝗶𝗼𝗻 𝗠𝗲𝘁𝗵𝗼𝗱 "
						+ type + "\n💳 𝗧𝗿𝗮𝗻𝘀𝗮𝗰𝘁𝗶𝗼𝗻 𝗔𝗺𝗼𝘂𝗻𝘁 " + accountService.convertCurrency(value)
						+ "\n💰 𝗔𝘃𝗮𝗶𝗹𝗮𝗯𝗹𝗲 𝗕𝗮𝗹𝗮𝗻𝗰𝗲 " + accountService.convertCurrency(balance)
						+ "\n\n[𝗣𝗿𝗲𝘀𝘀 𝗮𝗻𝘆𝘁𝗵𝗶𝗻𝗴] 𝘁𝗼 𝗿𝗲𝘁𝘂𝗿𝗻");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
