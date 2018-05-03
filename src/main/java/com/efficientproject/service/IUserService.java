package com.efficientproject.service;

import com.efficientproject.dto.UserDto;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.OrganizationAlreadyExistException;
import com.efficientproject.model.exceptions.UserAlreadyExistException;

public interface IUserService {

	User registerNewUserAccount(UserDto account)  throws UserAlreadyExistException, OrganizationAlreadyExistException;

}
