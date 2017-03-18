package org.cts.dashboard.service;

import java.util.List;

import org.cts.dashboard.model.Release;
import org.cts.dashboard.model.Risk;
import org.cts.dashboard.utility.DashboardSystemException;

public interface RiskServices {
	

	List<Risk> getAllRiskForId(String release_actual_id)
			throws DashboardSystemException;
}
