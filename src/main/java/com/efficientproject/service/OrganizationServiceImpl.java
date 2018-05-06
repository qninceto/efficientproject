package com.efficientproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efficientproject.persistance.dao.OrganizationReposiory;
import com.efficientproject.persistance.model.Organization;
import com.efficientproject.web.error.OrganizationAlreadyExistException;

@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService {
	
	@Autowired
	private OrganizationReposiory orgRepository;

	public Organization registerOrganization(String orgName) {
		
		if (organizationExists(orgName)) {
			throw new OrganizationAlreadyExistException("{UniqueOrganization.organization.name}" + orgName);
		}
		Organization organization = new Organization();
		organization.setName(orgName);
		return orgRepository.save(organization);
	}

	private boolean organizationExists(String name) {
		Organization org = orgRepository.findByName(name);
		return org != null;
	}
}
