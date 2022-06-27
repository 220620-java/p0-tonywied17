package wiedman.tony.bank;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import wiedman.tony.bank.data.PostgreSQLJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String username;
	private String password;
	private double balance;
	
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";
	
	//check table if not create
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

	// create account constructor
	public User(String name, String username, String password, String confirmPassword) {
		
		checkTable();
		
		if (name != null) {
			this.setName(name);
		} else {
			System.out.println("You must enter your full name!");
		}

		if (username != null) {
			this.setUsername(username);
		} else {
			System.out.println("You must enter a username!");
		}

		if (password != null) {
			this.setPassword(password);
		} else if (!password.equals(confirmPassword)) {
			System.out.println("Passwords do not match!");
		} else {
			System.out.println("You must enter a password!");
		}
		
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(DB_URL, USER, PASS);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			String stringBalance=String.valueOf(getBalance()); 
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

	// login constructor
	public User(String username2, String password2) {
		PostgreSQLJDBC sql = new PostgreSQLJDBC();
		// TODO Auto-generated constructor stub
		checkTable();
		
		
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);

	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM ACCOUNT;" );

	         
	         String compareInp = getUsername() + ":" + getPassword();
	         
	         while ( rs.next() ) {
	            int idDB = rs.getInt("id");
	            double balanceDB = rs.getDouble("BALANCE");
	            String nameDB  = rs.getString("NAME");
	            String usernameDB  = rs.getString("USERNAME");
	            String  passwordDB = rs.getString("PASSWORD");
	            String compareDB = usernameDB + ":" + passwordDB;
	            
	            if(compareDB.equals(compareInp)) {
	            	
	            	System.out.println(
	        				"/-------------------------------------------/"
	        				+ "\n"
	        				+ "* Big Bucks Bank - Account# " + this.getId() + " - " + this.getUsername() + "\n"
	        				+ "/-------------------------------------------/"
	        				+ "\n"
	        				+ "Balance: $" + this.getBalance() + "\n\n"
	        				+ "1.] Make Deposit"
	        				+ "\n"
	        				+ "2.] Make Withdrawal"
	        				+ "\n"
	        				+ "\n"
	        				+ "3.] Logout"
	        				+ "\n"
	        				+ "/-------------------------------------------/\n");

	            	//assign db account properties to User if user found
	            	this.setId(idDB);
	            	this.setName(nameDB);
	            	this.setUsername(usernameDB);
	            	this.setPassword(passwordDB);
	            	this.setBalance(balanceDB);
	            	
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

	public User() {
		// TODO Auto-generated constructor stub
	}

	// getting the balance method
	public double getBalance() {
		double roundedAmount = Math.round(balance * 100.0) / 100.0;
		return roundedAmount;
	}

	// Setting the balance method
	public void setBalance(double amount, int id) {
		PostgreSQLJDBC sql = new PostgreSQLJDBC();
		double roundedAmount = Math.round(amount * 100.0) / 100.0;
		
		
		
	}

	public void withdrawBalance(double amount) {

		if (amount > this.getBalance()) {
			System.out.println("/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Account - "
					+ getUsername() + "\n" + "/-------------------------------------------/" + "\n"
					+ "Overdraft not enabled.\n\n" + "Withdrawal for '$" + amount + "' could not be approved." + "\n"
					+ "\n" + "/-------------------------------------------/\n");
		} else {

			// set the new balance
			this.setBalance(getBalance() - amount, getId());

			System.out.println("/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Account - "
					+ getUsername() + "\n" + "/-------------------------------------------/" + "\n" + "\n"
					+ "Withdrawal for '$" + amount + "' completed!" + "\n" + "\n"
					+ "/-------------------------------------------/\n");

			System.out.println("Your current balance: $" + this.getBalance());
		}

	}

	public void depositBalance(double amount, int sqlid) {

		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL, USER, PASS);
	         c.setAutoCommit(false);
	         double newBalance = this.balance + amount;
	         stmt = c.createStatement();
	         String sql = "UPDATE ACCOUNT set BALANCE = "+newBalance+" where ID="+sqlid+";";
	         stmt.executeUpdate(sql);
	         c.commit();
	         stmt.close();
	         c.close();
	         this.balance = newBalance;
	         this.setBalance(getBalance() + amount, getId());
	         System.out.println("/-------------------------------------------/" + "\n" + "* Big Bucks Bank - Account - "
	 				+ getUsername() + "\n" + "/-------------------------------------------/" + "\n" + "\n" + "Deposit for '$"
	 				+ amount + "' completed!" + "\n" + "\n" + "/-------------------------------------------/\n");
	         
	         System.out.println(
     				"/-------------------------------------------/"
     				+ "\n"
     				+ "* Big Bucks Bank - Account# " + this.getId() + " - " + this.getUsername() + "\n"
     				+ "/-------------------------------------------/"
     				+ "\n"
     				+ "Balance: $" + this.balance + "\n\n"
     				+ "1.] Make Deposit"
     				+ "\n"
     				+ "2.] Make Withdrawal"
     				+ "\n"
     				+ "\n"
     				+ "3.] Logout"
     				+ "\n"
     				+ "/-------------------------------------------/\n");
	         
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }

	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", name=" + getName() + ", username=" + getUsername() + ", password=" + getPassword() + ", balance="
				+ getBalance() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBalance(double balance) {
		this.balance = balance;
		
		
	}

}
