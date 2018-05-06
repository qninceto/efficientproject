package com.efficientproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.efficientproject.persistance.dao.OrganizationReposiory;
import com.efficientproject.persistance.model.Organization;
import com.efficientproject.web.error.OrganizationAlreadyExistException;
import com.efficientproject.web.error.OrganizationException;

@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService {
	
	@Autowired
	private OrganizationReposiory orgRepository;
	
	@Autowired
    private MessageSource messages;

	public Organization registerOrganization(String orgName) {
		if(orgName.isEmpty()) {
			throw new OrganizationException(messages.getMessage("NotEmpty.organization.name", null, null));
		}
		if (organizationExists(orgName)) {
			throw new OrganizationAlreadyExistException(messages.getMessage("UniqueOrganization.organization.name", null, null) + orgName);
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
