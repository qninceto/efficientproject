package com.efficientproject.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "organizations")
public class Organization {
	
	@Id
	@GeneratedValue	
	private int id;
	
	@NotNull
	private String name;
	
	@OneToMany
	private List<User> users = new ArrayList<>();
	
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
