package org.cts.dashboard.dao;

import java.util.List;

import org.cts.dashboard.model.Modules;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ModuleDao {
	List<Modules> getAllModuleForId(int id) throws DashboardSystemException ;
	Modules createNewModule(int id,String name)  throws DashboardSystemException;
}
