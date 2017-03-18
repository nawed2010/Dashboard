package org.cts.dashboard.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;





import org.cts.dashboard.model.Risk;
import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
@Repository("RiskDao")
public class RiskDaoImpl implements RiskDao{
	private static transient final Logger LOGGER =LoggerFactory.getLogger(ModuleDaoImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	public List<Risk> getAllRiskForId(String release_actual_id) throws DashboardSystemException{
		List<Risk> risks = null;
	
		//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
		SqlParameterSource paramSource = new MapSqlParameterSource("release_actual_id", release_actual_id);
		try{
			risks = namedParameterJdbcTemplate.query(CommonConstants.SELECT_RISK_BY_ID, paramSource, new RiskMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
		System.out.println("there are no rows");
		}
		return risks;
	}

	public List<Risk> updateRisk(List<Risk> risks) throws DashboardSystemException{
		List<Risk> returnList= new ArrayList<Risk>(); 
		Risk returnedRisk= new Risk();
	for(Risk risk:risks){
		int releasePreset=0;
		int alreadypresent=0;
		int res=0;
	
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("release_actual_id", risk.getRelease_id());
			paramSource.addValue("risk_actual_id", risk.getRisk_actual_id());
			paramSource.addValue("description", risk.getDescription());
			paramSource.addValue("priority", risk.getPriority());
			paramSource.addValue("raised_date", risk.getRaised_date());
			paramSource.addValue("resolution_date", risk.getResolution_date());
			paramSource.addValue("remarks"
					+ "", risk.getRemarks());
			try
			{	
			releasePreset=namedParameterJdbcTemplate.queryForObject(CommonConstants.RELEASE_PRESENT, paramSource,Integer.class);
	}
	catch(EmptyResultDataAccessException ere){
		throw new DashboardSystemException("Email or Password is incorrect", ere);
	}catch (DataAccessException dae){
		throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
	}
			
			if (releasePreset!=0){
			
			alreadypresent = namedParameterJdbcTemplate.queryForObject(CommonConstants.CHECK_ALREADY_PRESENT, paramSource,Integer.class);
			if(alreadypresent!=0){
			try{
				res=namedParameterJdbcTemplate.update(CommonConstants.UPDATE_RISK, paramSource);
			}
			catch(EmptyResultDataAccessException ere){
				throw new DashboardSystemException("Email or Password is incorrect", ere);
			}catch (DataAccessException dae){
				throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
			}
					 returnedRisk= getRisk(risk.getDescription()); 
						returnList.add(returnedRisk);
						LOGGER.debug(" new risk has been updated");	
			}
			else{
				try
				{
				res=namedParameterJdbcTemplate.update(CommonConstants.INSERT_RISK, paramSource);
				}
				catch(EmptyResultDataAccessException ere){
					throw new DashboardSystemException("Email or Password is incorrect", ere);
				}catch (DataAccessException dae){
					throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
				}
                    returnedRisk= getRisk(risk.getDescription()); 
						returnList.add(returnedRisk);
						LOGGER.debug("risk has been added");
					 System.out.println(res);
				LOGGER.debug("res value",res);
			}
			
			}
			else{
				LOGGER.debug("release ID is not present");
			}
	}
		
		
	return returnList;
	}
	
	
	public Risk getRisk(String description) {
		Risk risk = new Risk();
		//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("description",description );
		try{
			risk = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_ADDED_RISK, paramSource, new RiskMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
		System.out.println("there are no rows");
		}
		return risk;
	}
	
	
	
	private class RiskMapper implements RowMapper<Risk> {
		@Override
		public Risk mapRow(ResultSet rs, int rowNum) throws SQLException {
			Risk  risk = new Risk(rs.getString("risk_actual_id"),
					rs.getString("release_actual_id"), rs.getString("description"), rs.getString("priority"),
					rs.getString("raised_date"), rs.getString("resolution_date"), rs.getString("remarks"));
			return risk;
		}
	  
		
	}



	



	
}
