package org.cts.dashboard.dao;

import java.util.List;


import org.cts.dashboard.model.Risk;
import org.cts.dashboard.utility.DashboardSystemException;

public interface RiskDao {
	List<Risk> getAllRiskForId(String release_actual_id) throws DashboardSystemException;
	List<Risk> updateRisk(List<Risk> risks) throws DashboardSystemException;
}
