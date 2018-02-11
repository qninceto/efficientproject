package com.efficientproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.POJO.User;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.util.IntegerChecker;
import com.google.gson.Gson;

/**
 * Servlet implementation class ReturnTasksPerState
 */
@WebServlet("/returnTasksPerState")
public class ReturnTasksPerStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");

			if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
				response.sendRedirect("./LogIn");
				return;
			}
			User user = (User) request.getSession().getAttribute("user");

			if (!user.isAdmin()) {
				request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
				return;
			}
			String projectIdParam = request.getParameter("projectId");
			if (projectIdParam != null && IntegerChecker.isInteger(projectIdParam)) {

				int projectId = Integer.parseInt(projectIdParam);
				String tasksPerStateJSON = new Gson()
						.toJson(IProjectDAO.getDAO(SOURCE_DATABASE).tasksNumberPerState(projectId));
				System.out.println(tasksPerStateJSON);
				response.getWriter().println(tasksPerStateJSON);
			} else {
				request.getRequestDispatcher("error2.jsp").forward(request, response);

			}
		} catch (IOException | ServletException | DBException | EfficientProjectDAOException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}
}
