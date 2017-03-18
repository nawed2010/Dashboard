package org.cts.dashboard.model;

import java.util.List;

public class Account {
	
	public Account() {
	};
	
	
	public Account(int id, String name) {
		super();
	this.id =id;
	this.name= name;
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

	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	private int id;
	private String name;
	private List<Project> projects;

}
