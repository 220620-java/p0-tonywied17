package tony.bank.service.methods;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.interfaces.AccountDAO;
import tony.bank.data.methods.AccountPostgres;
import tony.bank.service.interfaces.AccountService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class AccountServiceExec implements AccountService {
	Locale locale = new Locale("en", "US");
	private AccountDAO accountDao = new AccountPostgres();

	@Override
	public Account openAccount(Account account, User user, double deposit) {
		// TODO Auto-generated method stub

		accountDao.create(account, user, deposit);
		return null;
	}

	@Override
	public Account makeWithdraw(Account account, double amount) {
		// TODO Auto-generated method stub

		if (amount > account.getBalance()) {
			System.out.println("Innsufficient funds!");
		} else {
			double withdrawBalance = account.getBalance() - amount;
			accountDao.updateBalance(account, withdrawBalance, "withdrawal", amount);
			System.out.println("Withdrawal Complete! New");
		}
		return account;
	}

	@Override
	public Account makeDeposit(Account account, double amount) {

		double depositBalance = account.getBalance() + amount;

		accountDao.updateBalance(account, depositBalance, "deposit", amount);

		System.out.println("Deposit has been received:\n");
		return account;
	}

	@Override
	public Account getAccountInfo(Account account, User user) {
		accountDao.get(account, user);
		return account;

	}

	@Override
	public String convertCurrency(double d) {

		BigDecimal toDecimal = new BigDecimal(d);
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		String currencyBalance = currencyFormatter.format(toDecimal);

		return currencyBalance;
	}

	@Override
	public Account viewTransactions(Account account) {
		accountDao.printTrans(account);

		return account;

	}

}
