package com.efficientproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.POJO.Task;
import com.efficientproject.model.POJO.User;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.ITaskDAO;

/**
 * Servlet implementation class WorkerTasksServlet
 */
@WebServlet("/workertasks")
public class WorkerTasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession(false) != null || request.getSession(false).getAttribute("user") != null) {
				User user = (User) request.getSession().getAttribute("user");
				List<Task> tasks = ITaskDAO.getDAO(DAOStorageSourse.DATABASE).getAllTasksByUser(user.getId());
				request.setAttribute("tasks", tasks);

				RequestDispatcher rd = request.getRequestDispatcher("./workerTasks.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./LogIn");
			}
		} catch (Exception e) {
			try {
				response.sendRedirect("./error.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
