package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.model.Release;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ReleaseService {

	List<Release> getAllReleaseForId(int id) throws DashboardSystemException;

	Release addNewRelease(Release releaseData) throws DashboardSystemException;

}
