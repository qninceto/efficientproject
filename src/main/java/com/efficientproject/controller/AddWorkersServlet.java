package com.efficientproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.POJO.User;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.util.IntegerChecker;

/**
 * Servlet implementation class AddWorkersServlet
 */
@WebServlet("/addWorkers")
public class AddWorkersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			/**
			 * check if there is no session or there is but the user is different or the
			 * user is not admin:
			 */
			if (request.getSession(false) == null ||request.getSession(false).getAttribute("user") == null) {
				response.sendRedirect("./LogIn");
				return;
			}
				User user = (User) request.getSession().getAttribute("user");
				if (!user.isAdmin()) {
					request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
					return;
				}
				String projectIdParam=request.getParameter("projectId");
				if ( projectIdParam!= null && IntegerChecker.isInteger(projectIdParam)) {
						int projectId = Integer.parseInt(projectIdParam);
					/**
					 * check if the project is of this admin
					 */
					if (!IProjectDAO.getDAO(SOURCE_DATABASE).isThisProjectOfThisUser(projectId, user.getId())) {
						request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
						return;
					}
					
					request.setAttribute("projectId", projectId);
					RequestDispatcher dispatcher = request.getRequestDispatcher("./addWorker.jsp");
					dispatcher.forward(request, response);
					
				} else {
					request.getRequestDispatcher("error2.jsp").forward(request, response);
				}

		} catch (ServletException | IOException | DBException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}

}
