package com.efficientproject.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.efficientproject.model.entity.Organization;
import com.efficientproject.util.PasswordMatches;
import com.efficientproject.util.ValidEmail;
import com.efficientproject.util.ValidPassword;

@PasswordMatches
public class UserDto {

	@NotNull
	@Size(max = 45)
	@NotEmpty(message = "{NotNull.user.firstName}")
	private String firstName;

	@NotNull
	@Size(max = 45)
	@NotEmpty(message = "{NotNull.user.lastName}")
	private String lastName;

	@ValidEmail
	@NotNull
	@Size(max = 45)
	@NotEmpty(message = "{NotNull.user.email}")
	private String email;

	@NotNull
	@ValidPassword
	@Size(max = 45)
	private String password;

	private String matchingPassword;

	private String avatarPath;

	private boolean admin;

	private Organization organization;

	@NotNull
	private boolean isEmployed = false;

	public String getAvatarPath() {
		return avatarPath;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public Organization getOrganization() {
		return organization;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isEmployed() {
		return isEmployed;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}