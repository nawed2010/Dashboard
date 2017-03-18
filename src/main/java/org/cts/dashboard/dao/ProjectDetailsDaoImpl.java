package org.cts.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.cts.dashboard.model.ProjectDetails;
import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;




@Repository("ProjectDetailsDao")
public class ProjectDetailsDaoImpl implements ProjectDetailsDao {

	private transient final Logger LOGGER = LoggerFactory.getLogger(ProjectDetailsDaoImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<ProjectDetails> getResourceProjectStructureById(Integer id) throws DashboardSystemException {

		List<ProjectDetails> projectdetails = null;
		try{
			//Get User Roles
			LOGGER.info("---------Get resource Details by id="+id);
			SqlParameterSource userParamSource = new MapSqlParameterSource("id", id);
			

			projectdetails = namedParameterJdbcTemplate.query(CommonConstants.SELECT_PROJECTDETAILS_BY_RESOURCE_ID, userParamSource, new DetailsMapper());
		} catch (DataAccessException dae){
			throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
		}
		return projectdetails;

	
	}

	
	
	private class DetailsMapper implements RowMapper<ProjectDetails> {

		@Override
		public ProjectDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectDetails  projectdetail = new ProjectDetails(
					rs.getInt("account_id"), rs.getString("account_name"), rs.getInt("project_id"),
					rs.getString("project_name"), rs.getInt("modules_id"),
					rs.getString("modules_name"), rs.getInt("id"),
					rs.getString("name"), rs.getString("description"), rs.getString("release_actual_id"),rs.getInt("role"),
					rs.getString("DDD_delivery"), rs.getString("CUT_End_date"), rs.getString("QA_start_date"), rs.getString("QA_end_date"),
					rs.getString("UAT_start_date"), rs.getString("UAT_end_date"), rs.getString("Preview_Release"),
					rs.getString("Prod_Release"), rs.getString("type")
					  );
			projectdetail.setIs_deleted(rs.getBoolean("is_deleted"));
			return projectdetail;
		}
		
	}

	
	
	
	
	

	
}

