package com.efficientproject.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efficientproject.service.IUserService;
import com.efficientproject.util.GenericResponse;
import com.efficientproject.web.dto.UserDto;

@Controller
public class SignUpController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	/*private static final String SEND_EMAIL_SUBJECT = "efficientproject sign up";*/

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignUpForm() {
		return "signup.html";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public  GenericResponse signUpUser(@Valid final UserDto accountDto){
		
		LOGGER.debug("Registering user account with information: {}", accountDto);
		
		userService.registerNewUserAccount(accountDto);
		
		return new GenericResponse("success");
	}
		
		// SendingMails.sendEmail(email, SEND_EMAIL_SUBJECT,
		// messageContent(firstName,lastName,password));
		// user.setPassword(Encrypter.encrypt(password));
		
		// request.getSession().setAttribute("user", user);
	

//	private String messageContent(String firstName, String lastName, String password) {
//		return "Dear " + firstName + " " + lastName + ", "
//				+ "\n\n you succesfully signed into our website efficientproject.bj!" + "\n\n Your password is: "
//				+ password;
//	}
}
