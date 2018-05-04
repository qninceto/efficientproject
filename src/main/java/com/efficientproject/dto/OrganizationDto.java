package com.efficientproject.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationDto {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Size(max = 45, message ="{Size.organization.name}")
	private String name;

}
