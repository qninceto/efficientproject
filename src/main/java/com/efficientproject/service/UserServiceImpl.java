package com.efficientproject.service;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efficientproject.dto.UserDto;
import com.efficientproject.model.DAO.INFO;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.OrganizationAlreadyExistException;
import com.efficientproject.model.exceptions.UserAlreadyExistException;
import com.efficientproject.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final String DEFAUL_AVATAR_PATH = INFO.IMAGES_PATH + File.separator + "avatar-default.jpg";

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OrganizationServiceImpl orgServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public User registerNewUserAccount(UserDto accountDto){

		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("{UniqueUsername.user.username}" + accountDto.getEmail());
		}
		
		User user = new User();
		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setEmail(accountDto.getEmail());
		
		//encoding the password
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setAvatarPath(DEFAUL_AVATAR_PATH);
		boolean admin = accountDto.isAdmin();
		user.setAdmin(admin);// Arrays.asList("ROLE_USER","ROLE_ADMIN"));??
		if (admin) {
//			orgServiceImpl.registerOrganization(orgDto);
			
//			user.setOrganization(accountDto.getOrganization());
		}
		return repository.save(user);
	}

	

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		return user != null;
	}

}
