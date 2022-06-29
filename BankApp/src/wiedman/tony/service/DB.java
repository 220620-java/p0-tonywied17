package wiedman.tony.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	static final String DB_URL = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres";
	static final String USER = "postgres";
	static final String PASS = "Q!w2e3r4t5";
	
	Connection connection = null;

	// Create a DB connection
	public void connectDB() throws SQLException, Exception {
		
		Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);

	}

	// Close DB connection
	public void closeDB() throws SQLException {
		
			connection.close();
		
	}

}