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
class ServiceTest {

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
	void loginSuccess() {

		String username = "test", password = "test";

		User mockUser = new User(username, password);

		Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);

		User checkedUser = userService.logIn(username, password);

		assertEquals(username, checkedUser.getUsername());

	}

	@Test
	public void logInFailed() {
		// setup (inputs, mocks, etc.)
		String username = "404";
		String password = "notfound";

		Mockito.when(userDao.findByUsername(username)).thenReturn(null);

		// call the method that we're testing
		User returnedUser = userService.logIn(username, password);

		// assertion (checking for expected behavior)
		assertNull(returnedUser);
	}

	@Test
	void registerUser() {

		User mockUser = new User();

		mockUser.setUsername("mockTest");
		mockUser.setPassword("mockPassword");

		try {
			Mockito.when(userDao.create(mockUser)).thenReturn(mockUser);
		} catch (UsernameAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		User returnUser = null;
		try {
			returnUser = userService.registerUser(mockUser);
		} catch (UsernameAlreadyExistsException e) {
			// TODO Auto-generated catch block
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
	void openAccount() {

		User mockUser = new User();
		Account mockAccount = new Account();
		
		Mockito.when(accountDao.create(mockAccount, mockUser, 25)).thenReturn(mockAccount);

		Account returnAccount = accountService.openAccount(mockAccount, mockUser, 25);

		assertNotNull(returnAccount);
	}

}
