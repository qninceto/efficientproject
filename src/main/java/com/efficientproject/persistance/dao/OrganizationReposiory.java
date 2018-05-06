package com.efficientproject.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efficientproject.persistance.model.Organization;

public interface OrganizationReposiory extends JpaRepository<Organization, Integer> {
	Organization findByName(String name);
	Organization findById(int id);
}
