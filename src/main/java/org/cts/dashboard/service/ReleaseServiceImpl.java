package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.dao.ProjectDao;
import org.cts.dashboard.dao.ReleaseDao;
import org.cts.dashboard.model.Release;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReleaseService")
public class ReleaseServiceImpl implements ReleaseService{

	private transient final Logger LOGGER = LoggerFactory
			.getLogger(ReleaseServiceImpl.class);

	@Autowired
	private ReleaseDao releaseDao;
	
	
	public Release addNewRelease(Release release) throws DashboardSystemException{
	
	
		 String message;        
			List<Release> releases=null;
			releases= releaseDao.getAllReleaseForId(release.getModule_id());
	int create=0;
for(int i=0; i <releases.size();i++){
if(release.getRelease_actual_id().equalsIgnoreCase(releases.get(i).getRelease_actual_id().trim())){
	create++;
};
}
		if(create==0){
		release= releaseDao.createNewRelease(release);	
		}
		else
		{
			message="Release already present in database";
			LOGGER.info(" Release is already present with actual_id: "+release.getRelease_actual_id());
			
		}
		return release;
	}
	
	@Override
	public List<Release> getAllReleaseForId(int id) throws DashboardSystemException {
		return releaseDao.getAllReleaseForId(id);
	}

	
	
	
	
}
