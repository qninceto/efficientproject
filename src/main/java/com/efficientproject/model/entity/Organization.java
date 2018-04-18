package com.efficientproject.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "organizations")
public class Organization {

	private static final int MAX_LENGTH_INPUT_CHARACTERS = 45;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Size(max = MAX_LENGTH_INPUT_CHARACTERS, message ="Characters number limit reached-no more than " + MAX_LENGTH_INPUT_CHARACTERS + "allowed")
	private String name;

	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = User.class)
	private List<User> users = new ArrayList<>();

	public Organization(int id, String name) {
		this(name);
		this.id = id;
	}

	public Organization() {
	}

	public Organization(String name) {
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
