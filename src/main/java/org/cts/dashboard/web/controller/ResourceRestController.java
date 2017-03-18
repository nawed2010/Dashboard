package org.cts.dashboard.web.controller;

import java.util.List;

import org.cts.dashboard.model.Resource;
import org.cts.dashboard.model.RestErrorResponse;

import org.cts.dashboard.service.ResourceService;
import org.cts.dashboard.utility.CommonConstants;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceRestController {

	private transient final Logger LOGGER = LoggerFactory.getLogger(ResourceRestController.class);

	@Autowired
	private ResourceService resourceService;


	@RequestMapping(value="/login/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object login1(@RequestBody Resource credentials){

		Object response = null;
		HttpHeaders headers = new HttpHeaders();
		
		try{

			if(credentials!=null && !StringUtils.isEmpty(credentials.getEmail()) && !StringUtils.isEmpty(credentials.getPassword())){
				Resource resource = resourceService.login1(credentials);
				headers.set("message", "success");
				response = new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
			}else{
				throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_REQUIRED_REQUEST_PARAMETER_UNAVAILABLE);
			}

		}catch(DashboardSystemException e){
			LOGGER.error("Login Error", e);
			RestErrorResponse error = new RestErrorResponse();
			error.setErrorMessage(e.getMessage());
			headers.set("message", "failure");
			response = new ResponseEntity<RestErrorResponse>(error, headers, HttpStatus.BAD_REQUEST);
		}

		return response;

	}


	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getUserById(
			@PathVariable("id") Integer id
			)
	{

		Object response = null;
		HttpHeaders headers = new HttpHeaders();
		LOGGER.info("---------Get User Details by id="+id);

		try {
			if(id != null){
				Resource resource = resourceService.getResourceProjectById(id);
				headers.set("message", "success");
				response = new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
				LOGGER.info("---------Get response Details by id="+response);
			}else{
				throw new DashboardSystemException(CommonConstants.ERROR_MESSAGE_REQUIRED_REQUEST_PARAMETER_UNAVAILABLE);
			}
		} catch (DashboardSystemException e) {
			LOGGER.error("Error while Fetching user details by ID", e);
			RestErrorResponse error = new RestErrorResponse();
			error.setErrorMessage(e.getMessage());
			headers.set("message", "failure");
			response = new ResponseEntity<RestErrorResponse>(error, headers, HttpStatus.BAD_REQUEST);
		}

		return response;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

