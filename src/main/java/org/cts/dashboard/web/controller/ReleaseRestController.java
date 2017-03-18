package org.cts.dashboard.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.model.Release;
import org.cts.dashboard.service.AccountService;
import org.cts.dashboard.service.ProjectService;
import org.cts.dashboard.service.ReleaseService;
import org.cts.dashboard.utility.DashboardSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/release")
public class ReleaseRestController {

		private transient final Logger LOGGER = LoggerFactory.getLogger(ReleaseRestController.class);

		@Autowired
		private ReleaseService releaseService;


		@RequestMapping(value="/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody ResponseEntity<Release> addNewPRelease(@RequestBody Release releaseData) throws DashboardSystemException{
			String name= null;
			int account_id;
			Release release =null;
			Object response = null;
			HttpHeaders headers = new HttpHeaders();
			HttpHeaders responseHeaders = new HttpHeaders();
			LOGGER.debug("No release:",releaseData);
				if(releaseData!=null && !StringUtils.isEmpty(releaseData.getName())&& !StringUtils.isEmpty(releaseData.getRelease_actual_id()) ){
				
					release= releaseService.addNewRelease(releaseData);
				}
				
					responseHeaders.set("message", "Release is added to the account");
					return new ResponseEntity<Release>(release,responseHeaders,HttpStatus.OK);	
	}
			
		
		//fetching the account list
		@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Release>> getReleaseForId(@PathVariable("id") int id) throws DashboardSystemException{

			List<Release> releases = new ArrayList<Release>();
			releases.addAll(releaseService.getAllReleaseForId(id));
			return new ResponseEntity<List<Release>>(releases, HttpStatus.OK);
			
		}
	}
