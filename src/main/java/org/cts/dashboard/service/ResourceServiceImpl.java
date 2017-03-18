package org.cts.dashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cts.dashboard.dao.AccountDao;
import org.cts.dashboard.dao.ModulesDao;
import org.cts.dashboard.dao.ProjectDetailsDao;
import org.cts.dashboard.dao.ReleaseDao;
import org.cts.dashboard.dao.ProjectDao;
import org.cts.dashboard.dao.ResourceDao;
import org.cts.dashboard.model.Account;
import org.cts.dashboard.model.Modules;
import org.cts.dashboard.model.Release;
import org.cts.dashboard.model.Project;
import org.cts.dashboard.model.ProjectDetails;
import org.cts.dashboard.model.Resource;
import org.cts.dashboard.utility.CommonConstants;
import org.cts.dashboard.utility.DashboardSystemException;
import org.cts.dashboard.utility.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Contains business logic related to Users and User Roles
 * 
 * @author Dhiman Mondal
 * 
 */
@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService {

	private transient final Logger LOGGER = LoggerFactory
			.getLogger(ResourceServiceImpl.class);

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private ReleaseDao releaseDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProjectDetailsDao projectDetailsDao;
	@Override
	public Resource login1(Resource credentials)
			throws DashboardSystemException {
		
		Resource resource=resourceDao.login1(credentials);
		int id=resource.getId();
		String highestRole = resourceDao.getResourceRoleById(id);
		 resource.setHighestRole(highestRole);
		
		return resource;
		// Resource resource = ResourceDao.login(credentials);

		// shoot dummy mail
		/*
		 * List<String> to = null; if(!StringUtils.isEmpty(user.getEmail())){ to
		 * = new ArrayList<String>(); to.add(user.getEmail()); }
		 * emailService.sendMail(to, null,
		 * CommonConstants.EMAIL_SUBJECT_LOGGED_IN_NOTIFICATION,
		 * Utils.getEmailBodyForLogin(user));
		 */

		// return user;
	}

	private Release frameReleaseObject(ProjectDetails details) {
		Release release = new Release();
		release.setId(details.getId());
		release.setName(details.getName());
		release.setDescription(details.getDescription());
		release.setRole(details.getRole_id());
		release.setType(details.getType());
		release.setCUT_End_date(details.getCUT_End_date());
		release.setDDD_delivery(details.getDDD_delivery());
		release.setPreview_Release(details.getPreview_Release());
		release.setQA_end_date(details.getQA_end_date());
		release.setQA_start_date(details.getQA_start_date());
		release.setUAT_start_date(details.getUAT_start_date());
		release.setUAT_end_date(details.getUAT_end_date());
		
		release.setProd_Release(details.getProd_Release());
		release.setRelease_actual_id(details.getRelease_actual_id());
		
		return release;
	};

	@Override
	public Resource getResourceProjectById(Integer id)
			throws DashboardSystemException {

		Resource resource = resourceDao.getResourceById(id);
		String highestRole = resourceDao.getResourceRoleById(id);
            resource.setHighestRole(highestRole);
		List<ProjectDetails> projectDetails = new ArrayList<ProjectDetails>();

		projectDetails = projectDetailsDao.getResourceProjectStructureById(id);
		
		List<Account> accounts = new ArrayList<Account>();
		
		Account account = new Account();
		Project project = new Project();
		Modules module = new Modules();
		Release release = new Release();
		for (ProjectDetails projectdetail : projectDetails) {
			List<Release> releases = new ArrayList<Release>();
			List<Modules> modules = new ArrayList<Modules>();
			List<Project> projects = new ArrayList<Project>();
			boolean module_found = false;
			boolean project_found = false;
			boolean account_found = false;

			
			release = frameReleaseObject(projectdetail);
			for (int i = 0; i < accounts.size(); i++) {
				
				
				if (accounts.get(i).getId() == projectdetail.getL_account_id()) {
					account_found = true;
					 projects = new ArrayList<Project>();
					projects = accounts.get(i).getProjects();
					for (int j = 0; j < projects.size(); j++) {
						if (projects.get(j).getId() == projectdetail
								.getL_project_id()) {
							 modules = new ArrayList<Modules>();
							modules = projects.get(j).getModules();
							project_found = true;
							for (int k = 0; k < modules.size(); k++) {
								if (modules.get(k).getId() == projectdetail
										.getModule_id()) {
									releases = new ArrayList<Release>();
									releases = modules.get(k).getReleases();
									releases.add(release);
									modules.get(k).setReleases(releases);
									module_found = true;
								}
							}
							if (module_found == false) {
								 modules = new ArrayList<Modules>();
									releases = new ArrayList<Release>();
								releases.add(release);
								module= new Modules();
								module.setId(projectdetail.getModules_id());
								module.setName(projectdetail.getModules_name());
								module.setReleases(releases);
								modules.add(module);
								projects.get(j).setModules(modules);

							}

						}

					}
					if (project_found == false) {
						module= new Modules();
						project=new Project();
						releases = new ArrayList<Release>();
						 modules = new ArrayList<Modules>();
						 projects = new ArrayList<Project>();
						releases.add(release);
						module.setId(projectdetail.getModules_id());
						module.setName(projectdetail.getModules_name());
						module.setReleases(releases);
						modules.add(module);
						
						project.setId(projectdetail.getProject_id());
						project.setName(projectdetail.getProject_name());
						project.setModules(modules);
						projects.add(project);
						accounts.get(i).setProjects(projects);
					}
				}
			}
			if (account_found == false) {
				module= new Modules();
				account= new Account();
				project=new Project();
				releases = new ArrayList<Release>();
				 modules = new ArrayList<Modules>();
				 projects = new ArrayList<Project>();
				releases.add(release);
				module.setId(projectdetail.getModules_id());
				module.setName(projectdetail.getModules_name());
				module.setReleases(releases);
				modules.add(module);
		
				project.setId(projectdetail.getProject_id());
				project.setName(projectdetail.getProject_name());
				project.setModules(modules);
				projects.add(project);
				
				account.setProjects(projects);
				account.setId(projectdetail.getAccount_id());
				account.setName(projectdetail.getAccount_name());
				accounts.add(account);
			}

		}
		resource.setAccounts(accounts);
		return resource;
	}
}

