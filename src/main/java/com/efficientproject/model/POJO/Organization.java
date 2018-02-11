package com.efficientproject.model.POJO;

public class Organization {
	private int id;
	private String name;

	public Organization(int id, String name) {
		this(name);
		this.id = id;
	}

	public Organization( String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + "]";
	}


}
