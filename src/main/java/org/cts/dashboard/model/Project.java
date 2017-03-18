package org.cts.dashboard.model;

import java.util.List;

public class Project {
	
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(int id, String name) {
		super();
		this.id =id;
		this.name=name;
	}
	public List<Modules> getModules() {
		return modules;
	}
	public void setModules(List<Modules> modules) {
		this.modules = modules;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	private String name;
	private int id;
	private List<Modules> modules;
}
