package wiedman.tony.bank;

// Java packages
import java.util.Scanner;
import wiedman.tony.models.User;
//import wiedman.tony.service.SQL;

public class Main {

	public static User user = new User();
	static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) throws Exception {

		// Table creator
		// SQL.createTable();

	
		
		// MAIN MENU
		boolean usingBank = true;
		
			while (usingBank) {
				if (!user.isLoggedin()) {		
					
					System.out.println(
						"----------------------------------------------------\n" 
						+ " * MyBank Inc. - Welcome Back!\n"
						+ "----------------------------------------------------\n\n" 
						+ " 1.] Login to MyBank" + "\n"
						+ " 2.] Open an Account ($25 Min. Deposit)" 
						+ "\n" 
						+ "\n" 
						+ " 3.] Exit Bank" 
						+ "\n\n"
						+ "----------------------------------------------------\n" 
						+ "\n Type an option:");
				
					String input = scanner.nextLine();

					switch (input) {
					
						case "1":
							logIn();
							break;	
							
						case "2":
							openAccount();
							break;
							
						default:
							usingBank = false;
							System.out.println("Thank you for banking with MyBank Inc.");
					}
			}
				
			//(ACCOUNT MENU)
			else {
				
				System.out.println(	
					  " Welcome back, " + user.getName()+"\n" 
					+ "----------------------------------------------------\n" 
					+ " * MyBank Inc. - "+user.getAccountType()+" Account\n"
					+ "----------------------------------------------------\n" 
					+ " Account No: "+user.getAccountNumber()+"\n"
					+ "----------------------------------------------------\n" 
					+ "	Account Balance: $" + user.getBalance() 
					+ "\n\n" 
					+ "	1.] Make Deposit" 
					+ "\n"
					+ "	2.] Make Withdrawal"
					+ "\n" 
					+ "\n" 
					+ "	3.] Logout" 
					+ "\n" 
					+ "----------------------------------------------------\n" 
					+ "\nType an option:");

				String input = scanner.nextLine();
				

					switch (input) {
						case "1":
								System.out.println("Enter Deposit Amount: ");
							double depositAmount = scanner.nextDouble();
							
								user.makeDeposit(user, depositAmount);
								
							break;
						
						case "2":
								System.out.println("Enter Withdrawal Amount: ");
							double withdrawAmount = scanner.nextDouble();
							
								user.makeWithdraw(user, withdrawAmount);
							
							break;
						
						case "3":
							System.out.println("Logging out.");
							
								user.setLoggedin(false);
							
					}
					
				}

			}
			
			scanner.close();
	}
	
	
	
	// LOGIN MENU
	public static void logIn() throws Exception {
		
		boolean loggingIn = true;
		
			do {
				System.out.println("Username: ");
				String username = scanner.nextLine();
				
					user.setUsername(username);
					
				System.out.println("Password: ");
				String password = scanner.nextLine();
				
					user.setPassword(password);
					user.userLogin(user);
			
				if (!user.isFailed()) {
					loggingIn = false;			
				} else {				
					System.out.println("Could not find an account with supplied credentials.\n[Press anything to return to home]");
					scanner.nextLine();
				}
						
			} while (loggingIn);
			
		}
	
	
	
	// REGISTRATION MENU
	public static void openAccount() throws Exception {
		
		boolean openingAccount = true;
		
			do {
				System.out.println(
					"Account Type: \n"
					+ " 1.] Checking\n"
					+ " 2.] Savings");
			
				String type = scanner.nextLine();
			
					switch (type) {
					
						case "1":
							user.setAccountType("Checking");
							break;
							
						case "2":	
							user.setAccountType("Savings");
							break;
					}
					
					System.out.println("Enter your full name:");
				String nameInput = scanner.nextLine();
			
					System.out.println("Enter a username: ");
				String usernameInput = scanner.nextLine();
			
					System.out.println("Enter a password: ");
				String passwordInput = scanner.nextLine();
			
					System.out.println("Type \"y\" to confirm, \"n\" to reset registration, any other key to return.");
				String confirmInput = scanner.nextLine().toLowerCase();

					switch (confirmInput) {
					
						case "y":
							user.setName(nameInput);
							user.setUsername(usernameInput);
							user.setPassword(passwordInput);
							user.setBalance(25.00);

								user.createAccount(user);
								
							openingAccount = false;
							break;
						
						case "n":
							System.out.println("Starting account application over.");
							break;
						
						default:						
							System.out.println("Returning to main menu.");
							openingAccount = false;
					}
					
			} while (openingAccount);

	}
	
	
	
}


