package tony.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tony.bank.app.exceptions.UsernameAlreadyExistsException;
import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.interfaces.AccountDAO;
import tony.bank.data.interfaces.UserDAO;
import tony.bank.service.interfaces.AccountService;
import tony.bank.service.interfaces.UserService;
import tony.bank.service.methods.AccountServiceExec;
import tony.bank.service.methods.UserServiceExec;

@ExtendWith(MockitoExtension.class)
class BankAppUnitTest {

	@InjectMocks
	private static AccountService accountService = new AccountServiceExec();
	@InjectMocks
	private static UserService userService = new UserServiceExec();
	@Mock
	private AccountDAO accountDao;
	@Mock
	private UserDAO userDao;

	@BeforeAll
	public static void init() {

	}

	/*
	 * -- USER SERVICE TESTS
	 * 
	 * 
	 * METHODS TESTED:
	 * 
	 * User successful login
	 * 
	 * User failed login
	 * 
	 * User registration
	 * 
	 * User name already exists exception
	 * 
	 * 
	 */
	@Test
	void loginSuccessTest() {

		String username = "test", password = "test";

		User mockUser = new User(username, password);

		Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);

		User checkedUser = userService.logIn(username, password);

		assertEquals(username, checkedUser.getUsername());

	}

	@Test
	public void logInFailedTest() {
		String username = "404";
		String password = "notfound";

		Mockito.when(userDao.findByUsername(username)).thenReturn(null);

		User returnedUser = userService.logIn(username, password);

		assertNull(returnedUser);
	}

	@Test
	void registerUserTest() {

		User mockUser = new User();

		mockUser.setUsername("mockTest");
		mockUser.setPassword("mockPassword");

		try {
			Mockito.when(userDao.create(mockUser)).thenReturn(mockUser);
		} catch (UsernameAlreadyExistsException e1) {
			e1.printStackTrace();
		}

		User returnUser = null;
		try {
			returnUser = userService.registerUser(mockUser);
		} catch (UsernameAlreadyExistsException e) {
			e.printStackTrace();
		}

		assertNotNull(returnUser);
	}

	@Test
	public void registerUsernameAlreadyExists() throws UsernameAlreadyExistsException {
		User mockUser = new User();
		Mockito.when(userDao.create(mockUser)).thenReturn(null);

		assertThrows(UsernameAlreadyExistsException.class, () -> {
			userService.registerUser(mockUser);
		});
	}

	/*
	 * -- ACCOUNT SERVICE TESTS
	 * 
	 * 
	 * METHODS TESTED:
	 * 
	 * Allow a user to create an account
	 * 
	 * 
	 */

	@Test
	void openAccountTest() {

		User mockUser = new User();
		Account mockAccount = new Account();

		Mockito.when(accountDao.create(mockAccount, mockUser, 25, "Checking")).thenReturn(mockAccount);

		Account returnAccount = accountService.openAccount(mockAccount, mockUser, 25, "Checking");

		assertNotNull(returnAccount);
	}

	@Test
	void makeWithdrawTest() {

		Account mockAccount = new Account();
		mockAccount.setBalance(100);

		Mockito.when(accountDao.updateBalance(mockAccount, 75, "withdrawal", 25)).thenReturn(mockAccount);

		Account returnAccount = accountService.makeWithdraw(mockAccount, 25);

		assertEquals(75, returnAccount.getBalance());
	}

	@Test
	void makeDepositTest() {

		Account mockAccount = new Account();
		mockAccount.setBalance(100);

		Mockito.when(accountDao.updateBalance(mockAccount, 125, "deposit", 25)).thenReturn(mockAccount);

		Account returnAccount = accountService.makeDeposit(mockAccount, 25);

		assertEquals(125, returnAccount.getBalance());
	}

	@Test
	void getAccountInfoTest() {
		User mockUser = new User();
		Account mockAccount = new Account();

		mockAccount.setBalance(100);
		mockAccount.setId(1);

		Mockito.when(accountDao.get(mockAccount, mockUser)).thenReturn(mockAccount);

		Account returnAccount = accountDao.get(mockAccount, mockUser);

		assertEquals(100, returnAccount.getBalance());
	}

	@Test
	public void viewTransactionsTest() {

		Account mockAccount = new Account();

		Mockito.doNothing().when(accountDao).printTrans(mockAccount);

		Account returnedAccount = accountService.viewTransactions(mockAccount);

		assertNotNull(returnedAccount);
	}

}
