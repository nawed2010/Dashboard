package org.cts.dashboard.model;

import java.util.List;

public class Modules {

	public Modules(){
	}
	
	
	public Modules(int id, String name) {
		super();
		this.id=id;
		this.name=name;
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
	
	public List<Release> getReleases() {
		return releases;
	}

	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}

	private int id;
	private String name;
	private List<Release> releases;
}
