package com.efficientproject.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.efficientproject.dto.UserDto;
import com.efficientproject.model.entity.Organization;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.UserAlreadyExistException;
import com.efficientproject.service.IUserService;

@Controller
public class SignUpController {

//	private static final String SEND_EMAIL_SUBJECT = "efficientproject sign up";

	 @Autowired
	 private IUserService userService;

	 public SignUpController() {
			/*no op
			 */
	}
	 
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignUpForm(Model model) {
		
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		model.addAttribute("organization", new Organization()); //org dto

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public  ModelAndView signUpUser (@Valid UserDto accountDto, ModelMap map, BindingResult bindingResult) {

		User registered =new User();
		Organization organization = new Organization();
		map.addAttribute("user", accountDto);
		map.addAttribute("organization", organization);//org dto
		if (!bindingResult.hasErrors()) {
	        registered = createUserAccount(accountDto, bindingResult);
	    }
	    if (registered == null) {
	    	bindingResult.rejectValue("email", "message.regError");
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

	    // RequestDispatcher dispatcher = request.getRequestDispatcher("./signUp.jsp");

		// if (!CredentialsChecks.isPaswordStrong(password)) {
		// forwardWithErrorMessage(request, response, dispatcher,"Password must contain
		// 5 symbols and at least one number and letter");
		// return;
		// }
		//
		// if (IUserDAO.getDAO(SOURCE_DATABASE).isThereSuchAUser(email)) {
		// forwardWithErrorMessage(request, response, dispatcher,"User with such email
		// already exists, use another email !");
		// return;
		// }
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
	    if (bindingResult.hasErrors()) {
	    	return new ModelAndView("signup", "user", accountDto);
	    }else {
	    	return new ModelAndView("redirect:/profile-edit", "user", accountDto);//????
	    }
	}

	private User createUserAccount(UserDto accountDto, BindingResult result) {
	    User registered = null;
	    try {
	        registered = userService.registerNewUserAccount(accountDto);
	    } catch (UserAlreadyExistException e) {
	        return null;
	    }    
	    return registered;
	}
	

//	private String messageContent(String firstName, String lastName, String password) {
//		return "Dear " + firstName + " " + lastName + ", "
//				+ "\n\n you succesfully signed into our website efficientproject.bj!" + "\n\n Your password is: "
//				+ password;
//	}
}
