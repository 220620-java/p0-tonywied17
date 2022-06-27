package wiedman.tony.bank;


import java.util.Arrays;
import wiedman.tony.bank.*;
import wiedman.tony.bank.data.FileAccessObject;
import wiedman.tony.bank.ds.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static DecimalFormat deciFormat = new DecimalFormat();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bankApp();

		scanner.close();
	}

	public static void bankApp() {
		
		boolean userContinue = true;

		while (userContinue) {

			System.out.println(
					"/-------------------------------------------/"
					+ "\n"
					+ "* Welcome To Big Bucks Bank"
					+ "\n"
					+ "/-------------------------------------------/"
					+ "\n"
					+ "1.] Login"
					+ "\n"
					+ "2.] Create Account"
					+ "\n"
					+ "\n"
					+ "[Press anything else to exit]"
					+ "\n/"
					+ "-------------------------------------------/");
			String input = scanner.nextLine();

			switch (input) {
			case "1":
				// login
				
				System.out.println(""
						+ "/-------------------------------------------/"
						+ "\n"
						+ "* Big Bucks Bank - Login"
						+ "\n"
						+ "/-------------------------------------------/"
						+ "\n"
						+ "Username: ");
				String username = scanner.nextLine();
				System.out.println("Password: ");
				String password = scanner.nextLine();
				userContinue = false;
				loginMenu(username, password);
				break;
			case "2":
				// add a new user
				System.out.println("Your full name: ");
				String name = scanner.nextLine();
				System.out.println("Enter a username: ");
				String username1 = scanner.nextLine();
				System.out.println("Enter a password: ");
				String password1 = scanner.nextLine();
				System.out.println("Confirm your password: ");
				String password2 = scanner.nextLine();
				
				// new user overloaded constructor
				User newUser = new User(name, username1, password1, password2);
				break;
			default:
				userContinue = false;
				break;
			}

		}

	}

	public static void loginMenu(String username, String password) {

		User CheckCreds = new User(username, password);

		boolean userContinue = true;
		
		while (userContinue) {

			String input = scanner.nextLine();

			switch (input) {
			case "1":
				// make a deposit
				System.out.println("Enter Amount:");
				double depositAmount = scanner.nextDouble();
				
				CheckCreds.depositBalance(depositAmount);
				break;
			case "2":
				// make a withdrawal
				System.out.println("Enter Amount:");
				double withdrawAmount = scanner.nextDouble();
				CheckCreds.withdrawBalance(withdrawAmount);
				break;
			case "3":
				userContinue = false;
				bankApp();
				break;
			default:
				userContinue = false;
				bankApp();
				break;
			}
		}
	}

}
