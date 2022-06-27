package wiedman.tony.bank.data;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC {
static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
static final String USER = "postgres";
static final String PASS = "Q!w2e3r4t5";
	   
	public void checkTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = c.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS ACCOUNT" + 
			"(NAME           varchar(255)    NOT NULL, " + 
			" USERNAME       varchar(255)    NOT NULL, " + 
			" PASSWORD       varchar(255)    NOT NULL, " + 
			" BALANCE		 varchar(255)	 NOT NULL," +
			" ID  SERIAL PRIMARY KEY)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	
	//insert value method which is used for creating new users
	
	public void insertValue(String name, String username, String password, double balance) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			String stringBalance=String.valueOf(balance); 
			stmt = c.createStatement();
			String sql = "INSERT INTO ACCOUNT (NAME, USERNAME, PASSWORD, BALANCE)" + 
			"VALUES ('" + name + "', '" + username + "', '" + password + "', '" + stringBalance + "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Account opened successfully");
	}

	

	public void updateValue(String col, int value, int sqlid) {
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);

	         stmt = c.createStatement();
	         String sql = "UPDATE ACCOUNT set " + col + " = "+value+" where ID="+sqlid+";";
	         stmt.executeUpdate(sql);
	         c.commit();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}
	
	public void getCreds(String username, String password) {
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);

	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM ACCOUNT;" );

	         
	         String compareInp = username + ":" + password;
	         
	         while ( rs.next() ) {
	            int idDB = rs.getInt("id");
	            double balanceDB = rs.getDouble("BALANCE");
	            String usernameDB  = rs.getString("USERNAME");
	            String  passwordDB = rs.getString("PASSWORD");
	            String compareDB = usernameDB + ":" + passwordDB;
	            
	            if(compareDB.equals(compareInp)) {
	            	System.out.println(
	        				"/-------------------------------------------/"
	        				+ "\n"
	        				+ "* Big Bucks Bank - Account# " + idDB + " - " + usernameDB + "\n"
	        				+ "/-------------------------------------------/"
	        				+ "\n"
	        				+ "Balance: $" + balanceDB + "\n\n"
	        				+ "1.] Make Deposit"
	        				+ "\n"
	        				+ "2.] Make Withdrawal"
	        				+ "\n"
	        				+ "\n"
	        				+ "3.] Logout"
	        				+ "\n"
	        				+ "/-------------------------------------------/\n");
	            }else {
	            	System.out.println("No matching credentials found!");
	            }
	            
	            
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	         
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}

}
