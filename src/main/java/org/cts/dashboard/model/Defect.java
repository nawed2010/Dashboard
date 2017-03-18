package org.cts.dashboard.model;

public class Defect {
	 String defect_actual_id;
		String release_actual_id;
	    String description;
	    String priority;
	    String raised_date;
	    String resolution_date;
	    String remarks;
	    
	    
	    public Defect(String defect_actual_id,String release_actual_id, String description,String priority, String raised_date,
				String resolution_date, String remarks) {
	    	this.defect_actual_id=defect_actual_id;
	    	this.release_actual_id=release_actual_id;
	    	this.description=description;
	    	this.priority=priority;
	    	this.raised_date=raised_date;
	    	this.resolution_date=resolution_date;
	    	this.remarks=remarks;
		}
		public Defect() {
			// TODO Auto-generated constructor stub
		}
		public String getDefect_actual_id() {
			return defect_actual_id;
		}
		public void setDefect_actual_id(String defect_actual_id) {
			this.defect_actual_id = defect_actual_id;
		}
		public String getRelease_id() {
			return release_actual_id;
		}
		public void setRelease_id(String release_actual_id) {
			this.release_actual_id = release_actual_id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getRaised_date() {
			return raised_date;
		}
		public void setRaised_date(String raised_date) {
			this.raised_date = raised_date;
		}
		public String getResolution_date() {
			return resolution_date;
		}
		public void setResolution_date(String resolution_date) {
			this.resolution_date = resolution_date;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

}
