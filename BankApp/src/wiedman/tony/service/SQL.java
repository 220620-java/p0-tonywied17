package wiedman.tony.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

public class SQL {
	static DecimalFormat deciFormat = new DecimalFormat();
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
	
	
	public void updateValue(String col, double value, int id) {
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);

	         stmt = c.createStatement();
	         String sql = "UPDATE ACCOUNT set " + col + " = "+value+" where ID="+id+";";
	         stmt.executeUpdate(sql);
	         c.commit();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}
	
	public void selectValue(String col, int id) {
		checkTable();
		
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT "+col+" FROM ACCOUNT WHERE ID='"+id+"';");
			while (rs.next()) {

				int valueDB = rs.getInt("" + col + "");
				System.out.println(valueDB);

			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
		
	}

