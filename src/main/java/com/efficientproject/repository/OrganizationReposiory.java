package com.efficientproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efficientproject.model.entity.Organization;

public interface OrganizationReposiory extends JpaRepository<Organization, Integer> {
	Organization findByName(String name);
	Organization findById(int id);
}
