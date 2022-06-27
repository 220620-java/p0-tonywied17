package wiedman.tony.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DecimalFormat;
import wiedman.tony.bank.SQL;
import wiedman.tony.models.User;

public abstract class Lib implements AccountService {
	
	public void createAccount(User user) {
		
	}
	
	public void depositMoney(double amount, int id) {

	}
	
}
