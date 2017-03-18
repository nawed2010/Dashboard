package org.cts.dashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Release {
	
	


		
		public Release() {
			// TODO Auto-generated constructor stub
		}

	    public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getRelease_actual_id() {
			return release_actual_id;
		}


		public void setRelease_actual_id(String release_actual_id) {
			this.release_actual_id = release_actual_id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
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


		public int getRole() {
			return role;
		}

		public void setRole(int role) {
			this.role = role;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		public int getModule_id() {
			return module_id;
		}

		public void setModule_id(int module_id) {
			this.module_id = module_id;
		}
		

		public Release(int id, String release_actual_id, String name,
				String description, boolean is_deleted, String dDD_delivery,
				String cUT_End_date, String qA_start_date, String qA_end_date,
				String uAT_start_date, String uAT_end_date,
				String preview_Release, String prod_Release, String type,int role) {
			super();
			this.id = id;
			this.release_actual_id = release_actual_id;
			this.name = name;
			this.description = description;
			this.is_deleted = is_deleted;
			this.DDD_delivery = dDD_delivery;
			this.CUT_End_date = cUT_End_date;
			this.QA_start_date = qA_start_date;
			this.QA_end_date = qA_end_date;
			this.UAT_start_date = uAT_start_date;
			this.UAT_end_date = uAT_end_date;
			this.Preview_Release = preview_Release;
			this.Prod_Release = prod_Release;
			this.type = type;
			 this.role= role;
		}

		public Release(
				 int id, String name, String description, String release_actual_id,
					String dDD_delivery, String cUT_End_date, String qA_start_date,
					String qA_end_date, String uAT_start_date, String uAT_end_date,
					String preview_Release, String prod_Release, String type) {
		

			
			this.id = id;
			this.release_actual_id = release_actual_id;
			this.name = name;
			this.description = description;
			this.is_deleted = is_deleted;
			this.DDD_delivery = dDD_delivery;
			this.CUT_End_date = cUT_End_date;
			this.QA_start_date = qA_start_date;
			this.QA_end_date = qA_end_date;
			this.UAT_start_date = uAT_start_date;
			this.UAT_end_date = uAT_end_date;
			this.Preview_Release = preview_Release;
			this.Prod_Release = prod_Release;
			this.type = type;
		}

		private int id;
	    private String release_actual_id;
		private String name;
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
		private int role;
		private int module_id;
	
	

		
			

	}


