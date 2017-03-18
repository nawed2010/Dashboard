package org.cts.dashboard.service;



import java.util.List;

import org.cts.dashboard.dao.AccountDao;
import org.cts.dashboard.dao.ModuleDao;
import org.cts.dashboard.dao.ProjectDao;
import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Modules;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


	@Service("ModuleService")
	public class ModuleServiceImpl implements ModuleService{

		private transient final Logger LOGGER = LoggerFactory
				.getLogger(ModuleServiceImpl.class);

		@Autowired
		private ModuleDao moduleDao;
		
		
		public Modules addNewModule(int id,String name) throws DashboardSystemException{
			Modules module= null;
		
			 String message;        
				List<Modules> modules=null;
				modules= moduleDao.getAllModuleForId(id);
		int create=0;
	for(int i=0; i <modules.size();i++){
	if(name.equalsIgnoreCase(modules.get(i).getName().trim())){
		create++;
	};
	}
			if(create==0){
			module= moduleDao.createNewModule(id,name);	
			}
			else
			{
				message="Module already present in database";
				LOGGER.info(" Module is already present with name: "+name);
				
			}
			return module;
		}
		
		@Override
		public List<Modules> getAllModuleForId(int id) throws DashboardSystemException {
			return moduleDao.getAllModuleForId(id);
		}

		
		
		
		
}
