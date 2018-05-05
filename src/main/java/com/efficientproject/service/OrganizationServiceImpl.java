package com.efficientproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efficientproject.model.entity.Organization;
import com.efficientproject.repository.OrganizationReposiory;

@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService {
	
	@Autowired
	private OrganizationReposiory orgRepository;

//	public Organization registerOrganization() {
//		Organization organization = new Organization();
//		if (organizationExists(accountDto.getOrganization())) {
//			throw new OrganizationAlreadyExistException(
//					"{UniqueOrganization.organization.name}" + organization.getName());
//		}
//	}

	private boolean organizationExists(String name) {
		Organization org = orgRepository.findByName(name);
		if (org != null) {
			return true;
		}
		return false;
	}
}
