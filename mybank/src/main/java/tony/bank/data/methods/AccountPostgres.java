package tony.bank.data.methods;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public Account create(Account account, User user, double balance) {
		List<Account> allAccounts = new ArrayList<>();
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

				conn.commit();
				
				Account account1 = new Account(resultSet.getInt("id"), balance);
				
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

			String sql = "SELECT account.id as account_number , transactions.type, transactions.amount ,transactions.balance_after , transactions.id as transaction_id"
					+ "FROM Account"
					+ "LEFT JOIN transactions  ON account.id  = transactions.account_id"
					+ "where transactions.account_id ='25' ORDER BY account.owner_id ;";
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
	public Account updateBalance(Account account, double adjustedBalance, String type, double transAmount) {
		
		
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "UPDATE bank3.account SET balance=? WHERE id=?;";
			
			PreparedStatement transStmt = conn.prepareStatement(sql);
			
			transStmt.setDouble(1, adjustedBalance);	
			transStmt.setInt(2, account.getId());

			transStmt.executeUpdate();
		
			conn.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;

		
		// TODO Auto-generated method stub
/*
		try (Connection conn = connUtil.getConnection()) {

			String balanceQuery = "UPDATE bank3.account SET balance='?' WHERE id='?'";

			PreparedStatement balanceStmt = conn.prepareStatement(balanceQuery);
			balanceStmt.setDouble(1, amount);
			balanceStmt.setInt(2, account.getId());	
			
			balanceStmt.executeUpdate();
			
			

		
			String sql = "INSERT INTO bank3.transactions (id, account_id, type, amount, balance_after) VALUES (default, '?', '?', '?', '?');";
			PreparedStatement transStmt = conn.prepareStatement(sql);
			transStmt.setInt(1, account.getId());	
			transStmt.setString(2, type);
			transStmt.setDouble(3, transaction);
			transStmt.setDouble(4, amount);

			int rowsAffected = transStmt.executeUpdate();
			ResultSet resultSet = transStmt.getGeneratedKeys();
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

		return account;*/
		
	}

	
	
	@Override
	public void printTrans(Account account) {

		try (Connection conn = connUtil.getConnection()) {

			String sql = "SELECT * FROM bank3.transactions WHERE account_id=?";


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, account.getId());
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
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
