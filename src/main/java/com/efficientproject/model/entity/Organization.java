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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Size(max = 45, message ="{Size.organization.name}")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = User.class)
	private List<User> users = new ArrayList<>();

	public Organization() {
		/*no op
		 */
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + "]";
	}

}
