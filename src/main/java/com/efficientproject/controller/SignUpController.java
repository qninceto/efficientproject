package com.efficientproject.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efficientproject.dto.UserDto;
import com.efficientproject.service.IUserService;
import com.efficientproject.util.GenericResponse;

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
		
		// response.setCharacterEncoding("UTF-8");
		// request.setCharacterEncoding("UTF-8");
		// String firstName = escapeHtml4(request.getParameter("first-name")).trim();
		// String lastName = escapeHtml4(request.getParameter("last-name")).trim();
		// String email = escapeHtml4(request.getParameter("email")).trim();
		// String password = escapeHtml4(request.getParameter("password"));
		// String reppassword = escapeHtml4(request.getParameter("repPassword"));
		// String organization =
		// escapeHtml4(request.getParameter("organization")).trim();


		//
		// if
		// (IOrganizationDAO.getDAO(SOURCE_DATABASE).isThereSuchOrganization(organization))
		// {
		// forwardWithErrorMessage(request, response, dispatcher,"This organization is
		// already registered !");
		// return;
		// }
		//
		// if (isAdmin && organization.length()==0) {
		// forwardWithErrorMessage(request, response, dispatcher,"This organization name
		// is empty !");
		// return;
		// }

	    

		
		// SendingMails.sendEmail(email, SEND_EMAIL_SUBJECT,
		// messageContent(firstName,lastName,password));
		// user.setPassword(Encrypter.encrypt(password));
		
		// request.getSession().setAttribute("user", user);
//	    if (bindingResult.hasErrors()) {
//	    	return new ModelAndView("signup", "user", accountDto);
//	    }else {
//	    	return new ModelAndView("redirect:/profile-edit", "user", accountDto);//????
//	    }
	

//	private String messageContent(String firstName, String lastName, String password) {
//		return "Dear " + firstName + " " + lastName + ", "
//				+ "\n\n you succesfully signed into our website efficientproject.bj!" + "\n\n Your password is: "
//				+ password;
//	}
}
