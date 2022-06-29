/*
███╗░░░███╗██╗░░░██╗██████╗░░█████╗░███╗░░██╗██╗░░██╗  ░█████╗░██████╗░██████╗░
████╗░████║╚██╗░██╔╝██╔══██╗██╔══██╗████╗░██║██║░██╔╝  ██╔══██╗██╔══██╗██╔══██╗
██╔████╔██║░╚████╔╝░██████╦╝███████║██╔██╗██║█████═╝░  ███████║██████╔╝██████╔╝
██║╚██╔╝██║░░╚██╔╝░░██╔══██╗██╔══██║██║╚████║██╔═██╗░  ██╔══██║██╔═══╝░██╔═══╝░
██║░╚═╝░██║░░░██║░░░██████╦╝██║░░██║██║░╚███║██║░╚██╗  ██║░░██║██║░░░░░██║░░░░░
╚═╝░░░░░╚═╝░░░╚═╝░░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝  ╚═╝░░╚═╝╚═╝░░░░░╚═╝░░░░░
 
 𝙿𝚛𝚘𝚓𝚎𝚌𝚝 𝟶 - 𝚃𝚘𝚗𝚢 𝚆𝚒𝚎𝚍𝚖𝚊𝚗
 
 */

package wiedman.tony.bank;

//Java packages
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
							
						"""
						
███████████████████████████████████████████████████████████████████████████████████████████████████
█▄─▀█▀─▄█▄─█─▄█▄─▄─▀██▀▄─██▄─▀█▄─▄█▄─█─▄███▀▀▀▀▀████▄─█▀▀▀█─▄█▄─▄▄─█▄─▄███─▄▄▄─█─▄▄─█▄─▀█▀─▄█▄─▄▄─█
██─█▄█─███▄─▄███─▄─▀██─▀─███─█▄▀─███─▄▀██████████████─█─█─█─███─▄█▀██─██▀█─███▀█─██─██─█▄█─███─▄█▀█
▀▄▄▄▀▄▄▄▀▀▄▄▄▀▀▄▄▄▄▀▀▄▄▀▄▄▀▄▄▄▀▀▄▄▀▄▄▀▄▄▀▀▀▀▀▀▀▀▀▀▀▀▀▄▄▄▀▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▄▀		
								
						"""
							
						+	
							
						  "-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷\n" 
						+ " ᴄʜᴏᴏsᴇ ᴀɴ ᴏᴘᴛɪᴏɴ \n"
						+ "-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷\n\n" 
						+ " １．］ Ｌｏｇｉｎ ｔｏ ＭｙＢａｎｋ" + "\n"
						+ " ２．］ Ｏｐｅｎ ａｎ Ａｃｃｏｕｎｔ （＄２５ Ｄｅｐｏｓｉｔ）" 
						+ "\n" 
						+ "\n" 
						+ " ３．］ Ｅｘｉｔ ＭｙＢａｎｋ" 
						+ "\n\n"
						+ "-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-\n" 
						+ "\n Ｔｙｐｅ ａｎ ｏｐｔｉｏｎ：");
				
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
							System.out.println("Ｔｈａｎｋ ｙｏｕ ｆｏｒ ｂａｎｋｉｎｇ ｗｉｔｈ ＭｙＢａｎｋ Ｉｎｃ．");
					}
			}
				
			//(ACCOUNT MENU)
			else {

				System.out.println(	
						
					"""
								
							

████████████████████████████████████████████████████████████████████████████████████████████████████████████████
█▄─▀█▀─▄█▄─█─▄█▄─▄─▀██▀▄─██▄─▀█▄─▄█▄─█─▄███▀▀▀▀▀████▄─▀█▀─▄█▄─█─▄████▀▄─██─▄▄▄─█─▄▄▄─█─▄▄─█▄─██─▄█▄─▀█▄─▄█─▄─▄─█
██─█▄█─███▄─▄███─▄─▀██─▀─███─█▄▀─███─▄▀██████████████─█▄█─███▄─▄█████─▀─██─███▀█─███▀█─██─██─██─███─█▄▀─████─███
▀▄▄▄▀▄▄▄▀▀▄▄▄▀▀▄▄▄▄▀▀▄▄▀▄▄▀▄▄▄▀▀▄▄▀▄▄▀▄▄▀▀▀▀▀▀▀▀▀▀▀▀▄▄▄▀▄▄▄▀▀▄▄▄▀▀▀▀▄▄▀▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▀▀▄▄▄▀▀
								
								
						"""
					
						
					+ " Ｗｅｌｃｏｍｅ Ｂａｃｋ, " + user.getName()+"\n\n" 
					+ "-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷\n" 
					+ " * Ａｃｃ Ｎｏ. "+user.getAccountNumber()+"                                Ｔｙｐｅ．"+user.getAccountType()+"\n"
					+ "-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷-̷\n\n" 
					+ "	Ａｖａｉｌａｂｌｅ Ｂａｌａｎｃｅ:    | $" + user.getBalance() + " |"
					+ "\n\n" 
					+ "	１．］ Ｄｅｐｏｓｉｔ Ｆｕｎｄｓ" 
					+ "\n"
					+ "	２．］ Ｗｉｔｈｄｒａｗ Ｆｕｎｄｓ"
					+ "\n" 
					+ "\n" 
					+ "	３．］ Ｌｏｇｏｕｔ" 
					+ "\n\n" 
					+ "-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-̳-\n" 
					+ "\n Ｔｙｐｅ ａｎ ｏｐｔｉｏｎ：");

				String input = scanner.nextLine();
				

					switch (input) {
						case "1":
								System.out.println("Ｄｅｐｏｓｉｔ ａｍｏｕｎｔ： ");
							double depositAmount = scanner.nextDouble();
							
								user.makeDeposit(user, depositAmount);
							break;
						
						case "2":
								System.out.println("Ｗｉｔｈｄｒａｗ ａｍｏｕｎｔ： ");
							double withdrawAmount = scanner.nextDouble();
							
								user.makeWithdraw(user, withdrawAmount);
							break;
						
						case "3":
							System.out.println("Ｌｏｇｇｉｎｇ Ｏｕｔ．．．");
							
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
				System.out.println("Ｕｓｅｒｎａｍｅ： ");
				String username = scanner.nextLine();
				
					user.setUsername(username);
					
				System.out.println("Ｐａｓｓｗｏｒｄ： ");
				String password = scanner.nextLine();
				
					user.setPassword(password);
					user.userLogin(user);
			
				if (!user.isFailed()) {
					loggingIn = false;			
				} else {				
					System.out.println("Ｕｓｅｒ Ｎｏｔ Ｆｏｕｎｄ");
					scanner.nextLine();
				}
						
			} while (loggingIn);
			
		}
	
	
	
	// REGISTRATION MENU ~
	//
	//
	//
	//
	public static void openAccount() throws Exception {
		
		boolean openingAccount = true;
		
			do {
				System.out.println(
					"Ａｃｃｏｕｎｔ Ｔｙｐｅ \n"
					+ "	１．］ Ｃｈｅｃｋｉｎｇ  Ａｃｃｏｕｎｔ\n"
					+ "	２．］ Ｓａｖｉｎｇｓ Ａｃｃｏｕｎｔ");
				String type = scanner.nextLine();
			
					switch (type) {
					
						case "1":
							user.setAccountType("Checking");
							break;
							
						case "2":	
							user.setAccountType("Savings");
							break;
					}
					
					System.out.println("Ｅｎｔｅｒ Ｆｕｌｌ Ｎａｍｅ：");
				String nameInput = scanner.nextLine();
			
					System.out.println("Ｃｈｏｏｓｅ ａ Ｕｓｅｒｎａｍｅ： ");
				String usernameInput = scanner.nextLine();
			
					System.out.println("Ｃｈｏｏｓｅ ａ Ｐａｓｓｗｏｒｄ： ");
				String passwordInput = scanner.nextLine();
			
					System.out.println("Ｔｙｐｅ \"ｙ\" ｔｏ ｃｏｎｆｉｒｍ, \"n\" ｔｏ ｒｅｓｅｔ ｒｅｇｉｓｔｒａｔｉｏｎ， ａｎｙ ｏｔｈｅｒ ｋｅｙ ｔｏ ｒｅｔｕｒｎ．");
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
							System.out.println("Ｒｅｓｅｔｔｉｎｇ Ａｐｐｌｉｃａｔｉｏｎ．");
							break;
						
						default:						
							System.out.println("Ｒｅｔｕｒｎｉｎｇ ｔｏ Ｍａｉｎ Ｍｅｎｕ");
							openingAccount = false;
					}
					
			} while (openingAccount);

	}
	
	
	
}


