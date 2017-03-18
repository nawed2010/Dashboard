package org.cts.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Resource;
import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao{
	
	
	private static transient final Logger LOGGER =LoggerFactory.getLogger(AccountDaoImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	public List<Account> getAllAccount()throws DashboardSystemException {
		
		  List<Account> accounts=null;
		  accounts = namedParameterJdbcTemplate.query(CommonConstants.SELECT_All_ACCOUNT, new AccountMapper());
		LOGGER.debug("Total Accounts Retrieved:"+(accounts != null ? accounts.size() : 0));
		return accounts;
	
	}
	
	public Account getAccountByName(String name) {
		Account account = null;
		//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
		SqlParameterSource paramSource = new MapSqlParameterSource("name", name);
		try{
			account = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_ACCOUNT_BY_NAME, paramSource, new AccountMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
		System.out.println("there are no rows");
		}
		return account;
	}
	
	
	
	public Account createNewAccount(String name)
			throws DashboardSystemException {
		 Account account= null;
		SqlParameterSource paramSource = new MapSqlParameterSource("name", name);
		int res=0;
		res=namedParameterJdbcTemplate.update(CommonConstants.INSERT_INTO_ACCOUNT, paramSource);
		
		 if (res>0 ) {
			account= getAccountByName(name); 
		 }
		 
		 return account;
	}
	
	private class AccountMapper implements RowMapper<Account> {

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account(rs.getInt("id"), rs.getString("name"));
			return account;
		}
	}
}
	


