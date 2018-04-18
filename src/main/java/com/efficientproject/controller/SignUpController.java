package com.efficientproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.efficientproject.model.entity.Organization;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.interfaces.DAOStorageSourse;

@Controller
public class SignUpController {

	private static final String SEND_EMAIL_SUBJECT = "efficientproject sign up";
	private static final int MAX_LENGTH_INPUT_CHARACTERS = 45;
	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	protected String showSignUpForm(Model model) {

		model.addAttribute("user", new User());
		model.addAttribute("organization", new Organization());

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	protected String signUpUser (ModelMap map, BindingResult bindingResult) {

		@Valid User user =new User();
		Organization organization = new Organization();
		map.addAttribute("user", user);
		map.addAttribute("organization", organization);
		
		
		
		
		// response.setCharacterEncoding("UTF-8");
		// request.setCharacterEncoding("UTF-8");
		// String firstName = escapeHtml4(request.getParameter("first-name")).trim();
		// String lastName = escapeHtml4(request.getParameter("last-name")).trim();
		// String email = escapeHtml4(request.getParameter("email")).trim();
		// String password = escapeHtml4(request.getParameter("password"));
		// String reppassword = escapeHtml4(request.getParameter("repPassword"));
		// String organization =
		// escapeHtml4(request.getParameter("organization")).trim();
		// boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
		//
		// RequestDispatcher dispatcher = request.getRequestDispatcher("./signUp.jsp");
		//
		// if (firstName.length() > MAX_LENGTH_INPUT_CHARACTERS || lastName.length() >
		// MAX_LENGTH_INPUT_CHARACTERS
		// || email.length() > MAX_LENGTH_INPUT_CHARACTERS || password.length() >
		// MAX_LENGTH_INPUT_CHARACTERS
		// || organization.length() > MAX_LENGTH_INPUT_CHARACTERS) {
		// forwardWithErrorMessage(request, response, dispatcher,"Characters number
		// limit reached-no more than " + MAX_LENGTH_INPUT_CHARACTERS + "allowed");
		// return;
		// }
		// if(firstName.length()==0 || lastName.length()==0) {
		// forwardWithErrorMessage(request, response, dispatcher,"Empty first or last
		// name! Try Again");
		// return;
		// }
		//
		//
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
		//
		// // adding the user to the database:
		// User user = null;
		// int UserId;
		// if (!isAdmin) {
		// user = new User(firstName, lastName, email, password, false);
		// UserId = IUserDAO.getDAO(DAOStorageSourse.DATABASE).addUserWorker(user);
		// } else {
		// user = new User(firstName, lastName, email, password, true, new
		// Organization(organization));
		// UserId = IUserDAO.getDAO(DAOStorageSourse.DATABASE).addUserAdmin(user);
		// }
		// user.setId(UserId);
		// SendingMails.sendEmail(email, SEND_EMAIL_SUBJECT,
		// messageContent(firstName,lastName,password));
		// user.setPassword(Encrypter.encrypt(password));
		// request.getSession().setAttribute("user", user);
		// response.sendRedirect("./ProfileEdit");
		return "redirect:/profile-edit";
	}

	private void forwardWithErrorMessage(HttpServletRequest request, HttpServletResponse response,
			RequestDispatcher dispatcher, String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		dispatcher.forward(request, response);
	}

	private String messageContent(String firstName, String lastName, String password) {
		return "Dear " + firstName + " " + lastName + ", "
				+ "\n\n you succesfully signed into our website efficientproject.bj!" + "\n\n Your password is: "
				+ password;
	}
}
