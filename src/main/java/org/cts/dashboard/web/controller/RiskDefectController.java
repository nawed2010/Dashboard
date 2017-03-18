package org.cts.dashboard.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cts.dashboard.model.Release;
import org.cts.dashboard.model.Resource;
import org.cts.dashboard.model.RestErrorResponse;
import org.cts.dashboard.model.Risk;
import org.cts.dashboard.service.RiskServices;

import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class RiskDefectController {
	
	
	private transient final Logger LOGGER = LoggerFactory.getLogger(RiskDefectController.class);
	
	
	
	
	@Autowired
	private RiskServices riskservice;
	@RequestMapping(value="/{release_actual_id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Risk>> getRiskById(
			@PathVariable("release_actual_id") String release_actual_id
			)throws DashboardSystemException
	{


		HttpHeaders headers = new HttpHeaders();
		LOGGER.info("---------Get Risk Details by release_actual_id="+release_actual_id);


	
				List<Risk> risks = new ArrayList<Risk>();
			risks.addAll(riskservice.getAllRiskForId(release_actual_id));
				headers.set("message", "success");
				return new ResponseEntity<List<Risk>>(risks,headers,HttpStatus.OK);
		
		
		



	


	
}
}
