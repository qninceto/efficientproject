package com.efficientproject.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.efficientproject.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		/*
		 * no op
		 */
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserDto user = (UserDto) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}
}
