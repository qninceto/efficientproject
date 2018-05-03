package com.efficientproject.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efficientproject.dto.UserDto;
import com.efficientproject.model.DAO.INFO;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.UserAlreadyExistException;
import com.efficientproject.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final String DEFAUL_AVATAR_PATH = INFO.IMAGES_PATH + File.separator + "avatar-default.jpg";

	@Autowired
	private UserRepository repository;

	@Override

	public User registerNewUserAccount(UserDto account) throws UserAlreadyExistException {

		if (emailExist(account.getEmail())) {
			throw new UserAlreadyExistException("{UniqueUsername.user.username}" + account.getEmail());
		}
		User user = new User();
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setEmail(account.getEmail());
		user.setPassword(account.getPassword());
		user.setAvatarPath(DEFAUL_AVATAR_PATH);
		boolean admin = account.isAdmin();
		user.setAdmin(admin);// Arrays.asList("ROLE_USER","ROLE_ADMIN"));??
		if (admin) {
			user.setOrganization(account.getOrganization());
		}
		return repository.save(user);
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

}
