package org.cts.dashboard.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import javax.sql.DataSource;



import org.cts.dashboard.model.Modules;
import org.cts.dashboard.model.Release;
import org.cts.dashboard.model.Resource;
import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


@Repository("ReleaseDao")
	
	public class ReleaseDaoImpl implements ReleaseDao {

		private transient final Logger LOGGER = LoggerFactory.getLogger(ReleaseDaoImpl.class);

		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

		@Autowired
		public void setDataSource(DataSource dataSource){
			this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}

		@Override
		public List<Release> getResourceProjectByResourceId(Integer id) throws DashboardSystemException {

			List<Release> projects = null;
			try{
				
				SqlParameterSource userParamSource = new MapSqlParameterSource("id", id);
				projects = namedParameterJdbcTemplate.query(CommonConstants.SELECT_PROJECTS_BY_RESOURCE_ID, userParamSource, new ProjectMapper());
			} catch (DataAccessException dae){
				throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_SQL_EXCEPTION, dae);
			}
			return projects;

		
		}

		
		

		private class ProjectMapper implements RowMapper<Release> {
			@Override
			public Release mapRow(ResultSet rs, int rowNum) throws SQLException {
				Release  releases = new Release( );
				releases.setIs_deleted(rs.getBoolean("is_deleted"));
				return releases;
			}

		}




		@Override
		public List<Release> getAllReleaseForId(int id)
				throws DashboardSystemException {
			  List<Release> releases=null;
			  SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
			  releases = namedParameterJdbcTemplate.query(CommonConstants.SELECT_All_RELEASES_FOR_ID, paramSource, new ReleaseMapper());
			LOGGER.debug("Total Release Retrieved:"+(releases != null ? releases.size() : 0));
			return releases;
		
		}

		
	

		public Release getReleaseById(String id) throws DashboardSystemException{
			Release release = null;
			//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
			SqlParameterSource paramSource = new MapSqlParameterSource("release_actual_id", id);
			try{
				release = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_RELEASE_BY_ID, paramSource, new ReleaseMapper());
			}
			catch(EmptyResultDataAccessException ex)
			{
			System.out.println("there are no rows");
			}
			return release;
		}


		@Override
		public Release createNewRelease(Release release) throws DashboardSystemException {
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("module_id", release.getModule_id());
			paramSource.addValue("name", release.getName());
			paramSource.addValue("release_actual_id", release.getRelease_actual_id());
			paramSource.addValue("description", release.getDescription());
			paramSource.addValue("DDD_delivery", release.getDDD_delivery());
			paramSource.addValue("CUT_End_date", release.getCUT_End_date());
			paramSource.addValue("QA_start_date", release.getQA_start_date());
			paramSource.addValue("QA_end_date", release.getQA_end_date());
			paramSource.addValue("UAT_start_date", release.getUAT_start_date());
			paramSource.addValue("UAT_end_date", release.getUAT_end_date());
			paramSource.addValue("preview_Release", release.getPreview_Release());
			paramSource.addValue("prod_Release", release.getProd_Release());
			paramSource.addValue("Type", release.getType());

			
			int sel=0;
			sel=namedParameterJdbcTemplate.queryForObject(CommonConstants.FIND_INTO_RELEASE, paramSource, Integer.class); 
			LOGGER.debug("Total count value with the id in the release table", +sel);
			if(sel!=0){
			int res=0;
			res=namedParameterJdbcTemplate.update(CommonConstants.INSERT_INTO_RELEASE, paramSource);
			
			 if (res>0 ) {
				release= getReleaseById(release.getRelease_actual_id()); 
				LOGGER.debug("release has been added");
			 }
			}
			
			else
			{
				LOGGER.debug("No module exist for module_id:",release.getModule_id());
			}
			
			 return release;
		}

		private class ReleaseMapper implements RowMapper<Release> {
			@Override
			public Release mapRow(ResultSet rs, int rowNum) throws SQLException {
				Release  releases = new Release( rs.getInt("id"),
						rs.getString("name"), rs.getString("description"), rs.getString("release_actual_id"),
						rs.getString("DDD_delivery"), rs.getString("CUT_End_date"), rs.getString("QA_start_date"), rs.getString("QA_end_date"),
						rs.getString("UAT_start_date"), rs.getString("UAT_end_date"), rs.getString("Preview_Release"),
						rs.getString("Prod_Release"), rs.getString("type"));
				return releases;
			}

		}
	

		

		
}

