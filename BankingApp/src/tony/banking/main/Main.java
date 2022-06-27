package tony.banking.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tony.banking.data.FileAccessObject;
import tony.banking.ds.ArrayList;
import tony.banking.main.BankAccounts;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		bankApp();
		scanner.close();
	}
	
	private static void bankApp() {
		ArrayList<BankAccounts> accounts = null;
		FileAccessObject fileAccessObject = new FileAccessObject();
		
		File file = new File("banks.dat");

		try {
			accounts = (ArrayList<BankAccounts>) fileAccessObject.readObjects(file);
			if (accounts == null) {
				accounts = new ArrayList<>();
			}
		} catch (FileNotFoundException e) {
			// this is whatever we want to happen if the exception does get thrown
			e.printStackTrace();
		} catch (Exception e) {
			// this will handle any exceptions that aren't caught by the above
			// catch block(s)
			e.printStackTrace();
		} finally {
			// finally blocks are optional (when you have a try you either have to 
			// have at least one catch or a finally)
			//System.out.println("this always happens either way");
		}
		
		boolean userContinue = true;
		
		while (userContinue) {
			System.out.println("Type 1 to add a pet, "
					+ "type 2 to view pets, type anything else to exit");
			String input = scanner.nextLine();
			
			switch (input) {
			case "1":
				// add a pet
				System.out.println("Enter a pet name: ");
				String name = scanner.nextLine();
				System.out.println("Enter the pet's age: ");
				int age = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter the pet's species: ");
				String species = scanner.nextLine();
				System.out.println("Enter the pet's secret: ");
				String secret = scanner.nextLine();
				
				CreateAccount newPet = new CreateAccount(name, age, species, secret);
				
				pets.add(newPet);
				// thanks to generics, i can only put Pets in the
				// ArrayList now, and it is checked at compile time
				//pets.add(new Object());
				break;
			case "2":
				// view pets
				System.out.println(accounts);
				break;
			default:
				userContinue = false;
				break;
			}
		}
		
		fileAccessObject.saveObjects(file, accounts);
	}
}
