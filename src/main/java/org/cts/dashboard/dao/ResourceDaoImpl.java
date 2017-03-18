package org.cts.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;


import org.cts.dashboard.model.Resource;

import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;



@Repository("ResourceDao")
public class ResourceDaoImpl implements ResourceDao {

	private transient final Logger LOGGER = LoggerFactory.getLogger(ResourceDaoImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	@Override
	public Resource login1(Resource credentials) throws DashboardSystemException {
		Resource resource = null;
		try {
			//Get User by credentials
			SqlParameterSource paramSource = new MapSqlParameterSource("email", credentials.getEmail()).addValue("password", credentials.getPassword());
			resource = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_RESOURCE_BY_CREDENTIALS, paramSource, new ResourceMapper());
			if(resource.isDeleted()){
				LOGGER.info("Logging in was blocked for the deleted User:"+resource.getName());
				throw new DashboardSystemException("You do not have access to this portal anymore");
			}
			LOGGER.debug("Login successful for user with id:"+resource.getId()+" and email:"+resource.getEmail());
		}catch(EmptyResultDataAccessException ere){
			throw new DashboardSystemException("Email or Password is incorrect", ere);
		}catch (DataAccessException dae){
			throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
		}
		return resource;
	}

	

	private class ResourceMapper implements RowMapper<Resource> {

		@Override
		public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
			Resource resource = new Resource(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getInt("access_code"));
			resource.setDeleted(rs.getBoolean("is_deleted"));
			return resource;
		}

	}

	
	
	private class HighestRoleMapper implements RowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			int highestRole;
			highestRole = rs.getInt("id");
			
			return highestRole ;
		}

	}
	
	
	
	
	
	
	private class ResourceMapperWithCredentials implements RowMapper<Resource> {

		@Override
		public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
			Resource resource = new Resource(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
			resource.setDeleted(rs.getBoolean("is_deleted"));
			resource.setPassword(rs.getString("password"));
			return resource;
		}

	}
	
	
	public Resource getResourceById(Integer id) throws DashboardSystemException{
		
		Resource resource = null;
		try {

			//Get Resource
			SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
			resource = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_RESOURCE_BY_ID, paramSource, new ResourceMapper());

		}catch (DataAccessException dae){
			throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
		}

		return resource;
		
	}


	@Override
	public String getResourceRoleById(Integer id)
			throws DashboardSystemException {
		int highestRole;
		SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
		highestRole = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_HIGHEST_ROLE_BY_ID, paramSource, new HighestRoleMapper());
		
		String roleReadable="";
		
		switch(highestRole){
		
		case 1: roleReadable="Bussiness User";
				break;
		case 2: roleReadable="Manager";
				break;
		case 3: roleReadable="Team Lead";
				break;
		case 4: roleReadable="Developer";
				break;
		}
		
		return roleReadable;
	}
	
	
	/*public Projects getResourceProjectByResourceId(Integer id) throws DashboardSystemException{
		List<Projects> Project = null;
		try{
			//Get User Roles
			SqlParameterSource userParamSource = new MapSqlParameterSource("id", id);
			Project = namedParameterJdbcTemplate.query(CommonConstants.SELECT_PROJECTS_BY_RESOURCE_ID, userParamSource, new UserRoleMapper());
		} catch (DataAccessException dae){
			throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
		}
		return Project;

	}*/
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	


