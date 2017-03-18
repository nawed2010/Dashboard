package org.cts.dashboard.dao;

import java.util.List;





import org.cts.dashboard.model.Release;
import org.cts.dashboard.model.Resource;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ReleaseDao {
	
	List<Release> getResourceProjectByResourceId(Integer id)throws DashboardSystemException;
	List<Release> getAllReleaseForId(int id) throws DashboardSystemException;
	Release createNewRelease(Release release) throws DashboardSystemException;
}
