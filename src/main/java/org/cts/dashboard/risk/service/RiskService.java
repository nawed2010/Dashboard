package org.cts.dashboard.risk.service;

import java.io.IOException;
import java.util.List;




import org.cts.dashboard.model.Risk;
import org.cts.dashboard.utility.DashboardSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface RiskService {

	public List<Risk> saveRisk(MultipartFile fileObj, String name) throws IOException, DashboardSystemException; 
}
