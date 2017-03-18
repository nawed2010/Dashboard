package org.cts.dashboard.dao;

import java.util.List;

import org.cts.dashboard.model.ProjectDetails;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ProjectDetailsDao {
	List<ProjectDetails> getResourceProjectStructureById(Integer id) throws DashboardSystemException;

}
