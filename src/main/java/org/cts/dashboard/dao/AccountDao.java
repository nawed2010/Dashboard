package org.cts.dashboard.dao;

import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.utility.DashboardSystemException;
import org.springframework.stereotype.Repository;

@Repository("ADao")
public interface AccountDao  {
	
	List<Account> getAllAccount() throws DashboardSystemException ;
	Account createNewAccount(String name)  throws DashboardSystemException;
	Account getAccountByName(String name) throws DashboardSystemException ;
			
	
	
	
}
