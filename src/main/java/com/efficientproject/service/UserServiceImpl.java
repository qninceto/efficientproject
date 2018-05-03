package com.efficientproject.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efficientproject.dto.UserDto;
import com.efficientproject.model.DAO.INFO;
import com.efficientproject.model.entity.Organization;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.OrganizationAlreadyExistException;
import com.efficientproject.model.exceptions.UserAlreadyExistException;
import com.efficientproject.repository.OrganizationReposiory;
import com.efficientproject.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final String DEFAUL_AVATAR_PATH = INFO.IMAGES_PATH + File.separator + "avatar-default.jpg";

	@Autowired
	private UserRepository repository;

	@Autowired
	private OrganizationReposiory orgRepository;

	@Override

	public User registerNewUserAccount(UserDto accountDto)
			throws UserAlreadyExistException, OrganizationAlreadyExistException {

		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("{UniqueUsername.user.username}" + accountDto.getEmail());
		}
		User user = new User();
		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setEmail(accountDto.getEmail());
		user.setPassword(accountDto.getPassword());
		user.setAvatarPath(DEFAUL_AVATAR_PATH);
		boolean admin = accountDto.isAdmin();
		user.setAdmin(admin);// Arrays.asList("ROLE_USER","ROLE_ADMIN"));??
		if (admin) {
			Organization organization = new Organization();
			if (organizationExists(accountDto.getOrganization())) {
				throw new OrganizationAlreadyExistException(
						"{UniqueOrganization.organization.name}" + organization.getName());
			}
			user.setOrganization(accountDto.getOrganization());
		}
		return repository.save(user);
	}

	private boolean organizationExists(String name) {
		Organization org = orgRepository.findByName(name);
		if (org != null) {
			return true;
		}
		return false;
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

}
