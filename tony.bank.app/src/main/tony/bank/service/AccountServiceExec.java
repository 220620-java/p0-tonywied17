package tony.bank.service;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.access.AccountDAO;
import tony.bank.data.access.AccountPostgres;
import tony.bank.data.structure.List;

public class AccountServiceExec implements AccountService {

	private AccountDAO accountDao = new AccountPostgres();

	@Override
	public Account openAccount(Account account, User user) {
		// TODO Auto-generated method stub
		
		accountDao.create(account, user);
		return null;
	}


	@Override
	public Account makeWithdraw(Account account, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> viewAccounts() {
		// TODO Auto-generated method stub
		accountDao.findAll();
		return null;
	}

	@Override
	public Account makeDeposit(Account account, double amount) {
		// TODO Auto-generated method stub
		return null;
	}


}
