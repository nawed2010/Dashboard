package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.model.Resource;
import org.cts.dashboard.utility.DashboardSystemException;

public interface ResourceService {

		Resource login1(Resource credentials) throws DashboardSystemException;

		Resource getResourceProjectById(Integer id) throws DashboardSystemException;

}
