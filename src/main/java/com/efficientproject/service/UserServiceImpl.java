package com.efficientproject.service;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efficientproject.model.DAO.INFO;
import com.efficientproject.persistance.dao.UserRepository;
import com.efficientproject.persistance.model.Organization;
import com.efficientproject.persistance.model.User;
import com.efficientproject.web.dto.UserDto;
import com.efficientproject.web.error.OrganizationAlreadyExistException;
import com.efficientproject.web.error.UserAlreadyExistException;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final String DEFAUL_AVATAR_PATH = INFO.IMAGES_PATH + File.separator + "avatar-default.jpg";//fix this!!

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional//is this enough????
	@Override
	public User registerNewUserAccount(UserDto accountDto){

		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("{UniqueUsername.user.username}" + accountDto.getEmail());
		}
		
		User user = new User();
		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setEmail(accountDto.getEmail());
		
		//encoding the password:
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		
		user.setAvatarPath(DEFAUL_AVATAR_PATH);
		boolean admin = accountDto.isAdmin();
		user.setAdmin(admin);
		if (admin){
			String organizationName = accountDto.getOrganizationName();
			if(organizationName == null) {
				//throw exception!!!!
			}else {
				user.setOrganization(orgService.registerOrganization(organizationName));
			}
		}
		return repository.save(user);
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		return user != null;
	}

}
