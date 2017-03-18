package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ProjectService {
	 Project addNewProject(int id,String name) throws DashboardSystemException;
	 List getAllProjectForId(int id) throws DashboardSystemException;
}
