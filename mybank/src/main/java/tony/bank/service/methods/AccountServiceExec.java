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
	public Account openAccount(Account account, User user, double deposit, String accountType) {
		// TODO Auto-generated method stub

		accountDao.create(account, user, deposit, accountType);
		return account;
	}

	@Override
	public Account makeWithdraw(Account account, double amount) {
		// TODO Auto-generated method stub

		if (amount > account.getBalance()) {
			System.out.println("Innsufficient funds!");

		} else if (amount < 0) {
			System.out.println("Can't withdraw negative money");

		} else {
			double withdrawBalance = account.getBalance() - amount;
			accountDao.updateBalance(account, withdrawBalance);
			accountDao.insertTrans(account, withdrawBalance, "Withdrawal", amount);
			// System.out.println("Withdrawal Complete!");
			account.setBalance(withdrawBalance);
		}
		return account;
	}

	@Override
	public Account makeDeposit(Account account, double amount) {

		double depositBalance = account.getBalance() + amount;
		if (amount < 0) {
			System.out.println("Can't deposit negative money");

		} else {
			accountDao.updateBalance(account, depositBalance);
			accountDao.insertTrans(account, depositBalance, "Deposit", amount);
			account.setBalance(depositBalance);
			// System.out.println("Deposit has been received!\n");
		}
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
