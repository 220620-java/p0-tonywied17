package wiedman.tony.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;
import wiedman.tony.models.User;

public class SQL {
	
	
	
	public static DB db = new DB();
	
	
	
	// SQL QUERY FOR INERSTING A NEW USER INTO THE DATABASE
	public static User insertUser(User user) throws Exception {
		
		db.connectDB();
		
			// BANK.USERS TABLE
			String usersQuery = "INSERT INTO bank.users (NAME, USERNAME, PASSWORD)" + "VALUES ('" + user.getName() + "', '" + user.getUsername() + "', '" + user.getPassword() + "');";
		
			// BANK.ACCOUNTS TABLE
			String accountNumber = generateNumber();
			String accountQuery = "INSERT INTO bank.accounts (USERNAME, BALANCE, ACCOUNT_NUM, ACCOUNT_TYPE)" + "VALUES ('" + user.getUsername() + "', '" + user.getBalance() + "', '"+accountNumber+"', '"+user.getAccountType()+"');";
		
		Statement statement = db.connection.createStatement();
			statement.executeUpdate(usersQuery);
			statement.executeUpdate(accountQuery);
			statement.close();
			db.connection.commit();
		
		db.closeDB();
		return user;
		
	}
	
	
	
	// SQL QUERY FOR UPDATING THE ACCOUNT BALANCE FOR A USER
	public static void updateFunds(User user, double amount) throws Exception {
		
		DecimalFormat decim = new DecimalFormat("#.00");
		double formattedBalance = Double.parseDouble(decim.format(amount));
		
			user.setBalance(formattedBalance);
		
		db.connectDB();

			String balanceQuery = "UPDATE bank.accounts SET balance='" + formattedBalance + "' WHERE username='"+ user.getUsername() + "'";
			
		Statement statement = db.connection.createStatement();
			statement.executeUpdate(balanceQuery);
			statement.close();
			db.connection.commit();
			
		db.closeDB();
			
	}
	
	
	
	// SQL QUERY FOR LOGGING A USER IN (CHECK CREDENTIALS, ASSIGN VALUES)
	public static void selectUser(User user) throws Exception {
			
		db.connectDB();
				
			Statement statement = db.connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bank.users WHERE username='"+user.getUsername()+"'");
				while (result.next()) {
					// pull data from database and assign to variables
					int idDB = result.getInt("ID");
					String nameDB = result.getString("NAME"), usernameDB = result.getString("USERNAME"), passwordDB = result.getString("PASSWORD");
					
						if ((user.getUsername().equals(usernameDB)) && (user.getPassword().equals(passwordDB))) {
						
								selectAccountTable(user);
						
							user.setName(nameDB);
							user.setUsername(usernameDB);
							user.setPassword(passwordDB);
							user.setId(idDB);
							user.setFailed(false);
							user.setLoggedin(true);
						
					} else {
						
							user.setFailed(true);
							user.setLoggedin(false);
						
					}
				}
				
				result.close();
				statement.close();
				
		db.closeDB();
				
		}
	
		
		
	// SQL QUERY FOR ASSIGNING ACCOUNT TABLE INFORMATION SUCH AS BALANCE, ACCOUNT NUMBER, AND ACCOUNT TYPE
	public static void selectAccountTable(User user) throws Exception {
			
		db.connectDB();
				
			Statement statement = db.connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM bank.accounts WHERE username='" + user.getUsername() + "'");
			
				while (result.next()) {
					
					double balanceDB = result.getDouble("BALANCE");
					String accountNumDB = result.getString("ACCOUNT_NUM"), accountTypeDB = result.getString("ACCOUNT_TYPE");
				
						user.setBalance(balanceDB);
						user.setAccountNumber(accountNumDB);
						user.setAccountType(accountTypeDB);
						
				}
				
				result.close();
				statement.close();
				
		db.closeDB();

		}
	
		
		
	// CREATE TABLE METHOD (TESTING PURPOSES)
	public static void createTable() throws Exception {
		
		db.connectDB();

				String sql = "CREATE TABLE IF NOT EXISTS bank.accounts" 
					   + "(USERNAME     varchar(255)    NOT NULL, "
					   + " BALANCE		varchar(255)	NOT NULL," 
					   + " ACCOUNT_NUM	varchar(255)	NOT NULL,"
					   + " ACCOUNT_TYPE varchar(255)	NOT NULL,"
					   + " ID  SERIAL PRIMARY KEY)";
				
			Statement statement = db.connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();
				db.connection.commit();
			
		db.closeDB();
		
	}
	
	
	
	protected static String generateNumber() {
		
	    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    StringBuilder salt = new StringBuilder();
	    Random rnd = new Random();
	    
	    		while (salt.length() < 18) { 
	    			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	    			salt.append(SALTCHARS.charAt(index));
	    		}
	    
	    	String saltStr = salt.toString();
	    
	    return saltStr;

	}

}
