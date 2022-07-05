package wiedman.tony.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	static final String endpoint = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres", username = "postgres", password = "Q!w2e3r4t5";
	
	/*
	private static Connect connUtil;
	

	public static Connection dbConnect() {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(endpoint, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	

	Connect() {
		
	}
	
	
	// GETTER
	public static synchronized Connect getConnect() {
		if( connUtil == null) {
			connUtil = new Connect();
		}
		return connUtil;
	}

	
	*/

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