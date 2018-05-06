package com.efficientproject.service;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.efficientproject.model.DAO.INFO;
import com.efficientproject.persistance.dao.UserRepository;
import com.efficientproject.persistance.model.User;
import com.efficientproject.web.dto.UserDto;
import com.efficientproject.web.error.UserAlreadyExistException;
import com.efficientproject.web.error.UserDtoException;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final String DEFAUL_AVATAR_PATH = INFO.IMAGES_PATH + File.separator + "avatar-default.jpg";//fix this!!

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private MessageSource messages;

	@Transactional//is this enough????
	@Override
	public User registerNewUserAccount(UserDto accountDto){

		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("{UniqueUsername.user.username}" + accountDto.getEmail());
		}
		
		User user = new User();
		user.setFirstName(htmlEscape(accountDto.getFirstName()).trim());//utf-8 doesnt work?!
		user.setLastName(htmlEscape(accountDto.getLastName()).trim());
		user.setEmail(accountDto.getEmail());//trim or escapehtml?
		
		//encoding the password:
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		
		user.setAvatarPath(DEFAUL_AVATAR_PATH);
		
		boolean admin = accountDto.isAdmin();
		user.setAdmin(admin);
		
		String organizationName = accountDto.getOrganization();
		if(admin){
			if (organizationName == null) {
				throw new UserDtoException(messages.getMessage("Empty.organization.name", null, null));
			}else {
				organizationName=htmlEscape(accountDto.getOrganization()).trim();
				user.setOrganization(orgService.registerOrganization(organizationName));
			}
		}else {
			if(organizationName != null) {
				throw new UserDtoException(messages.getMessage("Empty.organization.name", null, null));
			}
		}
		return repository.save(user);
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		return user != null;
	}

}
