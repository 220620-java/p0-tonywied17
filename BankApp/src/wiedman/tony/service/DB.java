package wiedman.tony.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	static final String endpoint = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres", username = "postgres", password = "Q!w2e3r4t5";
	
	Connection connection = null;

	// CREATE DATABASE CONNECTION
	public void connectDB() throws SQLException, Exception {
		
		Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(endpoint, username, password);
			connection.setAutoCommit(false);

	}

	// CLOSE DATABASE CONNECTION
	public void closeDB() throws SQLException {
		
			connection.close();
		
	}

}