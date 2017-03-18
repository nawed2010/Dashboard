package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.model.Modules;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ModuleService {
	 Modules addNewModule(int id,String name) throws DashboardSystemException;
	 List getAllModuleForId(int id) throws DashboardSystemException;
}
