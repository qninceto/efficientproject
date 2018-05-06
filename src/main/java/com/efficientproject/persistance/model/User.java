package com.efficientproject.persistance.model;

import java.io.File;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.efficientproject.model.DAO.INFO;
import com.efficientproject.util.PasswordMatches;
import com.efficientproject.util.ValidEmail;
import com.efficientproject.util.ValidPassword;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	
	@Column(name = "first_name")
	private String firstName;
	
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String avatarPath;
	private boolean admin;
	
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Organization.class)// or eager?
	@JoinColumn(name = "organization_id", nullable = true)
	private Organization organization;
	
	@Column(name = "is_employed")
	private boolean isEmployed = false;


//	public User(int id, String firstName, String lastName, String email, String password, String avatarPath,
//			boolean admin, Organization organization, boolean isEmployed) {
//		this(firstName, lastName, email, password, admin, organization);
//		this.id = id;
//		this.avatarPath = avatarPath;
//		this.isEmployed = isEmployed;
//	}
//
//	public User(String firstName, String lastName, String email, String password, boolean admin) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.admin = admin;
//		this.avatarPath = DEFAUL_AVATAR_PATH;
//	}
//
//	public User(String firstName, String lastName, String email, String password, boolean admin,
//			Organization organization) {
//		this(firstName, lastName, email, password, admin);
//		this.organization = organization;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isEmployed() {
		return isEmployed;
	}

	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder
		.append("User [id=").append(id)
		.append(", firstName=").append(firstName)
		.append(", lastName=").append(lastName)
		.append(", email=").append(email)
		.append(", password=").append(password)
		.append(", avatarPath=").append(avatarPath)
		.append(", admin=").append(admin)
		.append(", organization=").append(organization)
		.append(", isEmployed=").append(isEmployed).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}



}
