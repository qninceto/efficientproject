package com.efficientproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.efficientproject.model.entity.User;

@Controller
public class LoginController  {
	@RequestMapping(value={"/login","/"},method = RequestMethod.GET)
	protected String showLogin() {
			return "index";
	}
	
	@RequestMapping(value= {"/login","/"},method = RequestMethod.POST)
	protected String login(@ModelAttribute("user") User user) {
//		try {
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
//
//			// Everything went well:
//			request.getSession().setAttribute("user", user);
//			response.sendRedirect("./dashboard");
		return "redirect:./dashboard";
//		} catch (DBException | EfficientProjectDAOException | ServletException | IOException e) {
//			try {
//				request.getRequestDispatcher("error.jsp").forward(request, response);
//				e.printStackTrace();
//			} catch (IOException | ServletException e1) {
//				e1.printStackTrace();
//			}
//		}
	}

}
