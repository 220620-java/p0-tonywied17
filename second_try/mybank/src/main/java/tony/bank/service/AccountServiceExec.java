package tony.bank.service;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.access.AccountDAO;
import tony.bank.data.access.AccountPostgres;
import tony.bank.data.structure.List;

public class AccountServiceExec {

	private AccountDAO accountDao = new AccountPostgres();

	public Account openAccount(Account account, User user, double deposit) {
		// TODO Auto-generated method stub
		
		accountDao.create(account, user, deposit);
		return null;
	}


	public Account makeWithdraw(Account account, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> viewAccounts() {
		// TODO Auto-generated method stub
		accountDao.findAll();
		return null;
	}

	public Account makeDeposit(Account account, double amount) {
		// TODO Auto-generated method stub
		return null;
	}


}