package com.efficientproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efficientproject.model.entity.User;

@Repository("userRepository")
	public interface UserRepository extends JpaRepository<User, Integer> {
		User findByEmail(String email);
		User findById(int id);
	}
