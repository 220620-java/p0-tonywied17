package wiedman.tony.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import wiedman.tony.models.User;

public class SQL {
	static DecimalFormat deciFormat = new DecimalFormat();
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";
	 Connection c = null;
     Statement statement = null;
	
	public void checkTable() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);

			statement = c.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS ACCOUNT" + 
			"(NAME           varchar(255)    NOT NULL, " + 
			" USERNAME       varchar(255)    NOT NULL, " + 
			" PASSWORD       varchar(255)    NOT NULL, " + 
			" BALANCE		 varchar(255)	 NOT NULL," +
			" ID  SERIAL PRIMARY KEY)";
			statement.executeUpdate(sql);
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void insertValue(String name, String username, String password, double balance) {
;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			String stringBalance=String.valueOf(balance); 
			statement = c.createStatement();
			String sql = "INSERT INTO ACCOUNT (NAME, USERNAME, PASSWORD, BALANCE)" + 
			"VALUES ('" + name + "', '" + username + "', '" + password + "', '" + stringBalance + "');";
			statement.executeUpdate(sql);

			statement.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Account opened successfully");
	}
	
	
	public void updateValue(String col, double value, int id) {

	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);

	         statement = c.createStatement();
	         String sql = "UPDATE ACCOUNT set " + col + " = "+value+" where ID="+id+";";
	         statement.executeUpdate(sql);
	         c.commit();
	         statement.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}
	
	public int loginUser(String username, String password) {
		
		checkTable();

		Connection c = null;
		Statement statement = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
			statement = c.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM ACCOUNT;");

			String compareInp = username + ":" + password;

			 while (result.next()) {
				 String usernameDB = result.getString("username");
				 String passwordDB = result.getString("password");
				 double balanceDB = result.getDouble("BALANCE");
				 int idDB = result.getInt("ID");
				 
				 String compareDB = usernameDB + ":" + passwordDB;
	                if(compareInp.equals(compareDB)) {
	                return idDB;
	                }
	            }
			result.close();
			statement.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return 0;
	
	}
	
	public void selectBalance(String col, int id) {
		checkTable();
		
		Connection c = null;
		Statement statement = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);

			statement = c.createStatement();
			ResultSet rs = statement.executeQuery("SELECT "+col+" FROM ACCOUNT WHERE ID='"+id+"';");
			while (rs.next()) {

				int valueDB = rs.getInt("" + col + "");
				double roundedAmount = Math.round(valueDB * 100.0) / 100.0;
				System.out.println(roundedAmount);

			}
			rs.close();
			statement.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
		
	
	
	}

