package org.cts.dashboard.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A user can have General/Admin access and one or more UserRole to perform
 * @author Dhiman Mondal
 *
 */
public class Resource {
	
	

	public enum Access {
		General(0), Admin(1);
		private int code;
		Access(int code){
			this.code = code;
		}
		public int getCode() {
			return code;
		}		
	}



	
	public Resource() {
		super();
	}
	
	/**
	 * @param id
	 * @param name
	 * @param email
	 */
	public Resource(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param accessCode
	 */
	public Resource(int id, String name, String email, int accessCode, String highestRole) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.highestRole=highestRole;
		for(Access access : Access.values()){
			if(access.getCode()==accessCode){
				setAccess(access);
				break;
			}
		}
	}
	
	
	public Resource(int id, String name, String email, int accessCode) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		
		for(Access access : Access.values()){
			if(access.getCode()==accessCode){
				setAccess(access);
				break;
			}
		}
			
	}
	


 	/*public List<ProjectDetails> getProjectdetails() {
		return projectdetails;
	}

	public void setProjectdetails(List<ProjectDetails> projectdetails) {
		this.projectdetails = projectdetails;
	}*/

	public Resource(String highestRole) {
		this.highestRole=highestRole;
	}


	private boolean reBoarded;
	

	public boolean isHasIncompletetasks() {
		return hasIncompletetasks;
	}

	public void setHasIncompletetasks(boolean hasIncompletetasks) {
		this.hasIncompletetasks = hasIncompletetasks;
	}

	public void setAccessCode(int accessCode) {
		this.accessCode = accessCode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
		this.accessCode = access.getCode();
	}





	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", access=" + access + ", accessCode=" + accessCode + "]";
	}

	public int getAccessCode() {
		return accessCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isReBoarded() {
		return reBoarded;
	}

	public void setReBoarded(boolean reBoarded) {
		this.reBoarded = reBoarded;
	}

	public String getHighestRole() {
		return highestRole;
	}

	public void setHighestRole(String highestRole) {
		this.highestRole = highestRole;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	private int id;	
	private String name;
	private String email;
	private String password;
	private Access access;
	private int accessCode;
	private String highestRole;;
	private int adminId;
	private boolean deleted;
	private boolean hasIncompletetasks;
	//private List<ProjectDetails> projectdetails;
    private List<Account> accounts;





/*	public void setResourceProjects(List<Projects> resourceProjectByResourceId) {
		// TODO Auto-generated method stub
		
	}*/

}
