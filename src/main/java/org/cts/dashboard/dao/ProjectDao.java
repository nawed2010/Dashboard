package org.cts.dashboard.dao;

import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.model.ProjectDetails;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ProjectDao {

	List<Project> getAllProjectForId(int id) throws DashboardSystemException ;
	Project createNewProject(int id,String name)  throws DashboardSystemException;
//	Project getAccountByName(String name) throws DashboardSystemException ;

}
