package org.cts.dashboard.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Modules;
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


	@Repository("ModuleDao")
	public class ModuleDaoImpl implements ModuleDao {
		
			private static transient final Logger LOGGER =LoggerFactory.getLogger(ModuleDaoImpl.class);
			
			private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
			
			@Autowired
			public void setDataSource(DataSource dataSource){
				this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			}

			@Override
			public List<Modules> getAllModuleForId(int id)throws DashboardSystemException {
				
				  List<Modules> modules=null;
				  SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
				  modules = namedParameterJdbcTemplate.query(CommonConstants.SELECT_All_MODULE_FOR_ID, paramSource, new ModuleMapper());
				LOGGER.debug("Total Modules Retrieved:"+(modules != null ? modules.size() : 0));
				return modules;
			
			}
			
			public Modules getModuleByName(String name) {
				Modules module = null;
				//SqlParameterSource paramSource1 = new MapSqlParameterSource("id", Integer.valueOf(id));
				SqlParameterSource paramSource = new MapSqlParameterSource("name", name);
				try{
					module = namedParameterJdbcTemplate.queryForObject(CommonConstants.SELECT_MODULE_BY_NAME, paramSource, new ModuleMapper());
				}
				catch(EmptyResultDataAccessException ex)
				{
				System.out.println("there are no rows");
				}
				return module;
			}
			
			
			@Override
			public Modules createNewModule(int id,String name)
					throws DashboardSystemException {
				Modules module= null;
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("project_id", id);
				paramSource.addValue("name", name);
				
				int sel=0;
				sel=namedParameterJdbcTemplate.queryForObject(CommonConstants.FIND_INTO_PROJECT, paramSource, Integer.class); 
				LOGGER.debug("Total count value with the id in the project table", +sel);
				if(sel!=0){
				int res=0;
				res=namedParameterJdbcTemplate.update(CommonConstants.INSERT_INTO_MODULE, paramSource);
				
				 if (res>0 ) {
					module= getModuleByName(name); 
					LOGGER.debug("module has been added");
				 }
				}
				
				else
				{
					LOGGER.debug("No project exist for id:",id);
				}
				
				 return module;
			}
			
			private class ModuleMapper implements RowMapper<Modules> {

				@Override
				public Modules mapRow(ResultSet rs, int rowNum) throws SQLException {
					Modules module = new Modules(rs.getInt("id"), rs.getString("name"));
					return module;
				}
			}

		

		
		}
			



		
			
			
			
			
			
		
			



