package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.dao.AccountDao;
import org.cts.dashboard.dao.ProjectDao;
import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


	@Service("ProjectService")
	public class ProjectServiceImpl implements ProjectService{

		private transient final Logger LOGGER = LoggerFactory
				.getLogger(ProjectServiceImpl.class);

		@Autowired
		private ProjectDao projectDao;
		
		
		public Project addNewProject(int id,String name) throws DashboardSystemException{
			Project project= null;
		
			 String message;        
				List<Project> projects=null;
				projects= projectDao.getAllProjectForId(id);
		int create=0;
	for(int i=0; i <projects.size();i++){
	if(name.equalsIgnoreCase(projects.get(i).getName().trim())){
		create++;
	};
	}
			if(create==0){
			project= projectDao.createNewProject(id,name);	
			}
			else
			{
				message="Project already present in database";
				LOGGER.info(" Project is already present with name: "+name);
				
			}
			return project;
		}
		
		@Override
		public List<Project> getAllProjectForId(int id) throws DashboardSystemException {
			return projectDao.getAllProjectForId(id);
		}

		
		
		
		
}
