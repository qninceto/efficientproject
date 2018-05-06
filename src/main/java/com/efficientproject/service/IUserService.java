package com.efficientproject.service;

import com.efficientproject.persistance.model.User;
import com.efficientproject.web.dto.UserDto;
import com.efficientproject.web.error.OrganizationAlreadyExistException;
import com.efficientproject.web.error.UserAlreadyExistException;

public interface IUserService {

	User registerNewUserAccount(UserDto account);

}
