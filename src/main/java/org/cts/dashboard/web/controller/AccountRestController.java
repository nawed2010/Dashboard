package org.cts.dashboard.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Resource;
import org.cts.dashboard.model.RestErrorResponse;
import org.cts.dashboard.service.AccountService;
import org.cts.dashboard.service.ResourceService;
import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountRestController {
	private transient final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

	@Autowired
	private AccountService accountService;


	@RequestMapping(value="/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Account> addNewAccount(@RequestBody Account accountData) throws DashboardSystemException{
		String name= null;
		Account account =null;
		Object response = null;
		HttpHeaders headers = new HttpHeaders();
		HttpHeaders responseHeaders = new HttpHeaders();

			if(accountData!=null && !StringUtils.isEmpty(accountData.getName())){
				name=accountData.getName();
				account= accountService.addNewAccount(name);
			}
			
				responseHeaders.set("message", "Account is added");
				return new ResponseEntity<Account>(account,responseHeaders,HttpStatus.OK);	
}
		
	
	//fetching the account list
	@RequestMapping(value="/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Account>> getAllAccount() throws DashboardSystemException{
	
			List<Account> accounts = new ArrayList<Account>();
			accounts.addAll(accountService.getAllAccount());
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
		}
}
	
	
	
	
	
	
	
	

