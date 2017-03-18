package org.cts.dashboard.model;

import java.util.List;

public class ProjectDetails {




	


	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public int getL_account_id() {
		return l_account_id;
	}
	public void setL_account_id(int l_account_id) {
		this.l_account_id = l_account_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public int getL_project_id() {
		return l_project_id;
	}
	public void setL_project_id(int l_project_id) {
		this.l_project_id = l_project_id;
	}
	public int getModules_id() {
		return modules_id;
	}
	public void setModules_id(int modules_id) {
		this.modules_id = modules_id;
	}
	public String getModules_name() {
		return modules_name;
	}
	public void setModules_name(String modules_name) {
		this.modules_name = modules_name;
	}
	public int getRelease_id() {
		return release_id;
	}
	public void setRelease_id(int release_id) {
		this.release_id = release_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelease_actual_id() {
		return release_actual_id;
	}
	public void setRelease_actual_id(String release_actual_id) {
		this.release_actual_id = release_actual_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDDD_delivery() {
		return DDD_delivery;
	}
	public void setDDD_delivery(String dDD_delivery) {
		DDD_delivery = dDD_delivery;
	}
	public String getCUT_End_date() {
		return CUT_End_date;
	}
	public void setCUT_End_date(String cUT_End_date) {
		CUT_End_date = cUT_End_date;
	}
	public String getQA_start_date() {
		return QA_start_date;
	}
	public void setQA_start_date(String qA_start_date) {
		QA_start_date = qA_start_date;
	}
	public String getQA_end_date() {
		return QA_end_date;
	}
	public void setQA_end_date(String qA_end_date) {
		QA_end_date = qA_end_date;
	}
	public String getUAT_start_date() {
		return UAT_start_date;
	}
	public void setUAT_start_date(String uAT_start_date) {
		UAT_start_date = uAT_start_date;
	}
	public String getUAT_end_date() {
		return UAT_end_date;
	}
	public void setUAT_end_date(String uAT_end_date) {
		UAT_end_date = uAT_end_date;
	}
	public String getPreview_Release() {
		return Preview_Release;
	}
	public void setPreview_Release(String preview_Release) {
		Preview_Release = preview_Release;
	}
	public String getProd_Release() {
		return Prod_Release;
	}
	public void setProd_Release(String prod_Release) {
		Prod_Release = prod_Release;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		type=type;
	}
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}



	public ProjectDetails(int account_id, String account_name,
			 int project_id, String project_name,
			 int modules_id, String modules_name,
		 int id, String name, String description, String release_actual_id,
			int role_id, 
			String dDD_delivery, String cUT_End_date, String qA_start_date,
			String qA_end_date, String uAT_start_date, String uAT_end_date,
			String preview_Release, String prod_Release, String type
		) {
		super();
		this.account_id = account_id;
		this.account_name = account_name;
		this.l_account_id = l_account_id;
		this.project_id = project_id;
		this.project_name = project_name;
		this.l_project_id = l_project_id;
		this.modules_id = modules_id;
		this.modules_name = modules_name;
		this.id = id;
		this.name = name;
		this.release_actual_id = release_actual_id;
		this.role_id = role_id;
		this.description = description;
		this.DDD_delivery = dDD_delivery;
		this.CUT_End_date = cUT_End_date;
		this.QA_start_date = qA_start_date;
		this.QA_end_date = qA_end_date;
		this.UAT_start_date = uAT_start_date;
		this.UAT_end_date = uAT_end_date;
		this.Preview_Release = preview_Release;
		this.Prod_Release = prod_Release;
		this.type = type;
		this.module_id = module_id;
	}



	private int account_id;
	private String account_name;
	private int l_account_id;
	private int project_id;
	private String project_name;
	private int l_project_id;
	private int modules_id;
	private String modules_name;
	private int release_id;
	private int id;


	private String name;

    private String release_actual_id;
	private int role_id;
	private String description;
	private boolean is_deleted;
	private String DDD_delivery;
	private String CUT_End_date;
	private String QA_start_date;
	private String QA_end_date;
	private String UAT_start_date;
	private String UAT_end_date;
	private String Preview_Release;
	private String Prod_Release;
	private String type;
	private int module_id;
	
	
	
	




}
