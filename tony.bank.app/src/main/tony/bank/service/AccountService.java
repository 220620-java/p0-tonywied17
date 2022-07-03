package tony.bank.service;

import tony.bank.app.model.Account;
import tony.bank.app.model.User;
import tony.bank.data.structure.List;

public interface AccountService {

	public Account makeDeposit(Account account, double amount);
	
	public Account makeWithdraw(Account account, double amount);

	public Account openAccount(Account account, User user, double deposit);
	
	public List<Account> viewAccounts();

}
