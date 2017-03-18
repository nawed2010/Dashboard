package org.cts.dashboard.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;





import org.cts.dashboard.model.Defect;
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
@Repository("DefectDao")
public class DefectDaoImpl implements DefectDao{
	private static transient final Logger LOGGER =LoggerFactory.getLogger(DefectDaoImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	public List<Defect> getAllDefectForId(String release_actual_id) throws DashboardSystemException{
		List<Defect> defects = null;
	
		//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
		SqlParameterSource paramSource = new MapSqlParameterSource("release_actual_id", release_actual_id);
		try{
			defects = namedParameterJdbcTemplate.query(CommonConstants.SELECT_DEFECT_BY_ID, paramSource, new DefectMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
		System.out.println("there are no rows");
		}
		return defects;
	}

	public List<Defect> updateDefect(List<Defect> defects) throws DashboardSystemException{
		List<Defect> returnList= new ArrayList<Defect>(); 
		Defect returnedDefect= new Defect();
	for(Defect defect:defects){
		int releasePreset=0;
		int alreadypresent=0;
		int res=0;
	
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("release_actual_id", defect.getRelease_id());
			paramSource.addValue("defect_actual_id", defect.getDefect_actual_id());
			paramSource.addValue("description", defect.getDescription());
			paramSource.addValue("priority", defect.getPriority());
			paramSource.addValue("raised_date", defect.getRaised_date());
			paramSource.addValue("resolution_date", defect.getResolution_date());
			paramSource.addValue("remarks"
					+ "", defect.getRemarks());
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
			
			alreadypresent = namedParameterJdbcTemplate.queryForObject(CommonConstants.CHECK_ALREADY_PRESENT_DEFECT, paramSource,Integer.class);
			if(alreadypresent!=0){
			try{
				res=namedParameterJdbcTemplate.update(CommonConstants.UPDATE_DEFECT, paramSource);
			}
			catch(EmptyResultDataAccessException ere){
				throw new DashboardSystemException("Email or Password is incorrect", ere);
			}catch (DataAccessException dae){
				throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
			}
					 returnedDefect= getDefect(defect.getDescription()); 
						returnList.add(returnedDefect);
						LOGGER.debug(" new defect has been updated");	
			}
			else{
				try
				{
				res=namedParameterJdbcTemplate.update(CommonConstants.INSERT_DEFECT, paramSource);
				}
				catch(EmptyResultDataAccessException ere){
					throw new DashboardSystemException("Email or Password is incorrect", ere);
				}catch (DataAccessException dae){
					throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
				}
                    returnedDefect= getDefect(defect.getDescription()); 
						returnList.add(returnedDefect);
						LOGGER.debug("defect has been added");
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
	
	
	public Defect getDefect(String description) {
		Defect defect = new Defect();
		//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("description",description );
		try{
			defect = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_ADDED_DEFECT, paramSource, new DefectMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
		System.out.println("there are no rows");
		}
		return defect;
	}
	
	
	
	private class DefectMapper implements RowMapper<Defect> {
		@Override
		public Defect mapRow(ResultSet rs, int rowNum) throws SQLException {
			Defect  defect = new Defect(rs.getString("defect_actual_id"),
					rs.getString("release_actual_id"), rs.getString("description"), rs.getString("priority"),
					rs.getString("raised_date"), rs.getString("resolution_date"), rs.getString("remarks"));
			return defect;
		}
	  
		
	}



	



	
}
