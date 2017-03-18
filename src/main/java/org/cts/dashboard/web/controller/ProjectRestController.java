package org.cts.dashboard.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.service.AccountService;
import org.cts.dashboard.service.ProjectService;
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
@RequestMapping("/project")
public class ProjectRestController {

		private transient final Logger LOGGER = LoggerFactory.getLogger(ProjectRestController.class);

		@Autowired
		private ProjectService projectService;


		@RequestMapping(value="/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody ResponseEntity<Project> addNewProject(@RequestBody Project projectData) throws DashboardSystemException{
			String name= null;
			int account_id;
			Project project =null;
			Object response = null;
			HttpHeaders headers = new HttpHeaders();
			HttpHeaders responseHeaders = new HttpHeaders();

				if(projectData!=null && !StringUtils.isEmpty(projectData.getName())&& !StringUtils.isEmpty(projectData.getId()) ){
					account_id =projectData.getId();
					name=projectData.getName();
					project= projectService.addNewProject(account_id,name);
				}
				
					responseHeaders.set("message", "Project is added to the account");
					return new ResponseEntity<Project>(project,responseHeaders,HttpStatus.OK);	
	}
			
		
		//fetching the account list
		@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Project>> getProjectForId(@PathVariable("id") int id) throws DashboardSystemException{

			List<Project> projects = new ArrayList<Project>();
			projects.addAll(projectService.getAllProjectForId(id));
			return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
			
		}
	}
		

