package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.utility.DashboardSystemException;

public interface AccountService {
    
	 Account addNewAccount(String name) throws DashboardSystemException;
	 List getAllAccount() throws DashboardSystemException;
}
