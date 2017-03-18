package org.cts.dashboard.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Modules;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.service.AccountService;
import org.cts.dashboard.service.ModuleService;
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
@RequestMapping("/module")
public class ModuleRestController {

		private transient final Logger LOGGER = LoggerFactory.getLogger(ModuleRestController.class);

		@Autowired
		private ModuleService moduleService;


		@RequestMapping(value="/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody ResponseEntity<Modules> addNewProject(@RequestBody Modules moduleData) throws DashboardSystemException{
			String name= null;
			int project_id;
			Modules module =null;
			Object response = null;
			HttpHeaders headers = new HttpHeaders();
			HttpHeaders responseHeaders = new HttpHeaders();

				if(moduleData!=null && !StringUtils.isEmpty(moduleData.getName())&& !StringUtils.isEmpty(moduleData.getId()) ){
					project_id =moduleData.getId();
					name=moduleData.getName();
					module= moduleService.addNewModule(project_id,name);
				}
				
					responseHeaders.set("message", "Module is added to the account");
					return new ResponseEntity<Modules>(module,responseHeaders,HttpStatus.OK);	
	}
			
		
		//fetching the account list
		@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Modules>> getProjectForId(@PathVariable("id") int id) throws DashboardSystemException{

			List<Modules> modules = new ArrayList<Modules>();
			modules.addAll(moduleService.getAllModuleForId(id));
			return new ResponseEntity<List<Modules>>(modules, HttpStatus.OK);
			
		}
	}