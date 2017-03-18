package org.cts.dashboard.dao;

import java.util.List;

import org.cts.dashboard.model.Resource;

import org.cts.dashboard.utility.DashboardSystemException;

public interface ResourceDao {
	Resource login1(Resource credentials) throws DashboardSystemException;

	Resource getResourceById(Integer id) throws DashboardSystemException;

	String getResourceRoleById(Integer id) throws DashboardSystemException;

	//Resource getResourceProject_structureByUserId(Integer id) throws DashboardSystemException;

	//Resource getResourceProjectByResourceId(Integer id)throws DashboardSystemException;

}
