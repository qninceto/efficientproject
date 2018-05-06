package com.efficientproject.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IUserDAO;
import com.efficientproject.persistance.model.User;
import com.efficientproject.util.IntegerChecker;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class ProfileDetail
 */
@WebServlet("/profileDetail")
public class ProfileDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession(false) == null || request.getSession(false).getAttribute("user")==null) {
				response.sendRedirect("./LogIn");
				return;
			}
			
			String userIdParam = request.getParameter("userId");
			if (userIdParam != null && IntegerChecker.isInteger(userIdParam)) {

				int userId = Integer.parseInt(userIdParam);
				User user = IUserDAO.getDAO(DAOStorageSourse.DATABASE).getUserById(userId);
				request.setAttribute("user", user);
				request.getRequestDispatcher("./userDetail.jsp").forward(request, response);
				
			} else {
				request.getRequestDispatcher("error2.jsp").forward(request, response);
			}
		} catch (DBException | EfficientProjectDAOException | IOException | ServletException e) {
			try {
				response.sendRedirect("error.jsp");
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
