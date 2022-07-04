package tony.bank.data.connect;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDB {
	private static ConnectDB connDB;
	static final String endpoint = "jdbc:postgresql://bankapp.cwhrhowdulyu.us-east-1.rds.amazonaws.com:5432/postgres",
			username = "postgres", password = "Q!w2e3r4t5";

	private ConnectDB() {
	};

	public static synchronized ConnectDB getConnectionDB() {
		if (connDB == null) {
			connDB = new ConnectDB();
		}
		return connDB;
	}

	public Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(endpoint, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
