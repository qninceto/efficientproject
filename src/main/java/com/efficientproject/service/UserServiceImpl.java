package com.efficientproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.UserAlreadyExistException;
import com.efficientproject.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository repository;
	@Override
	
	public User registerNewUserAccount(User account) throws UserAlreadyExistException {
		 
        if (emailExist(account.getEmail())) {   
            throw new UserAlreadyExistException(
              "There is an account with that email address:"  + account.getEmail());
        }
        User user = new User();    
        user.setFirstName(account.getFirstName());
        user.setLastName(account.getLastName());
        user.setPassword(account.getPassword());
        user.setEmail(account.getEmail());
        user.setRoles(Arrays.asList("ROLE_USER"));
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
