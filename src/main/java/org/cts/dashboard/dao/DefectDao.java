package org.cts.dashboard.dao;

import java.util.List;

import org.cts.dashboard.model.Defect;
import org.cts.dashboard.model.Risk;
import org.cts.dashboard.utility.DashboardSystemException;

public interface DefectDao {
	List<Defect> getAllDefectForId(String release_actual_id) throws DashboardSystemException;
	List<Defect> updateDefect(List<Defect> refects) throws DashboardSystemException;
}
