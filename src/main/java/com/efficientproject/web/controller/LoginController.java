package com.efficientproject.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.efficientproject.persistance.model.User;
import com.efficientproject.util.GenericResponse;

public class LoginController {
	
//			request.setCharacterEncoding("UTF-8");
//			String email = request.getParameter("email");
//			String password = request.getParameter("password");
//
//			RequestDispatcher dispatcher = request.getRequestDispatcher("./index.jsp");
//
//			// there is no user with this email:
//			if (!IUserDAO.getDAO(DAOStorageSourse.DATABASE).isThereSuchAUser(email)) {
//				request.setAttribute("errorMessage", "There is no user registered with this email!");
//				dispatcher.forward(request, response);
//				return;
//			}
//
//			// there is user with this email but password doesn`t match:
//			User user = IUserDAO.getDAO(DAOStorageSourse.DATABASE).getUserByEmail(email);
//
//			if ( !user.getPassword().equals(Encrypter.encrypt(password))) {
//				request.setAttribute("errorMessage", "Password not matching the user, try again!");
//				dispatcher.forward(request, response);
//				return;
//			}

		//			request.getSession().setAttribute("user", user);
		//return new GenericResponse("success"); //"redirect:/dashboard";

}
