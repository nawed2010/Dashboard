package org.cts.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;



import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.model.ProjectDetails;
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


@Repository("ProjectDao")
public class ProjectDaoImpl implements ProjectDao {
	
		private static transient final Logger LOGGER =LoggerFactory.getLogger(ProjectDaoImpl.class);
		
		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
		
		@Autowired
		public void setDataSource(DataSource dataSource){
			this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}

		@Override
		public List<Project> getAllProjectForId(int id)throws DashboardSystemException {
			
			  List<Project> projects=null;
			  SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
			  projects = namedParameterJdbcTemplate.query(CommonConstants.SELECT_All_PROJECT_FOR_ID, paramSource, new ProjectMapper());
			LOGGER.debug("Total Projects Retrieved:"+(projects != null ? projects.size() : 0));
			return projects;
		
		}
		
		public Project getProjectByName(String name) {
			Project project = null;
			//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
			SqlParameterSource paramSource = new MapSqlParameterSource("name", name);
			try{
				project = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_PROJECT_BY_NAME, paramSource, new ProjectMapper());
			}
			catch(EmptyResultDataAccessException ex)
			{
			System.out.println("there are no rows");
			}
			return project;
		}
		
		
		@Override
		public Project createNewProject(int id,String name)
				throws DashboardSystemException {
			Project project= null;
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("account_id", id);
			paramSource.addValue("name", name);
			
			int sel=0;
			sel=namedParameterJdbcTemplate.queryForObject(CommonConstants.FIND_INTO_ACCOUNT, paramSource, Integer.class); 
			LOGGER.debug("Total count value with the id in the account table", +sel);
			if(sel!=0){
			int res=0;
			res=namedParameterJdbcTemplate.update(CommonConstants.INSERT_INTO_PROJECT, paramSource);
			
			 if (res>0 ) {
				project= getProjectByName(name); 
				LOGGER.debug("project has been added");
			 }
			}
			
			else
			{
				LOGGER.debug("No account exist for id:",id);
			}
			
			 return project;
		}
		
		private class ProjectMapper implements RowMapper<Project> {

			@Override
			public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
				Project project = new Project(rs.getInt("id"), rs.getString("name"));
				return project;
			}
		}

	

	
	}
		



	
		
		
		
		
		
	
		

