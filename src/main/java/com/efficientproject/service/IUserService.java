package com.efficientproject.service;

import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.UserAlreadyExistException;

public interface IUserService {

	User registerNewUserAccount(User account)  throws UserAlreadyExistException;

}
