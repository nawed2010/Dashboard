package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.dao.AccountDao;
import org.cts.dashboard.dao.ResourceDao;
import org.cts.dashboard.model.Account;
import org.cts.dashboard.utility.DashboardSystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("AccountService")
public class AccountServiceImpl implements AccountService{

	private transient final Logger LOGGER = LoggerFactory
			.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao accountDao;
	
	
	public Account addNewAccount(String name) throws DashboardSystemException{
		Account account= null;
		 String message;        
			List<Account> accounts=null;
accounts= accountDao.getAllAccount();
	int create=0;
for(int i=0; i <accounts.size();i++){
if(name.equalsIgnoreCase(accounts.get(i).getName())){
	create++;
};
}
		if(create==0){
		account= accountDao.createNewAccount(name);	
		}
		else
		{
			message="Account already present in database";
			LOGGER.info(" Account is already present with name: "+name);
			
		}
		return account;
	}
	
	@Override
	public List<Account> getAllAccount() throws DashboardSystemException {
		return accountDao.getAllAccount();
	}

	
	
	
	
}
