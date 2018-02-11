
package com.efficientproject.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.ITaskDAO;

/**
 * Servlet implementation class FinisheTaskServlet
 */
@WebServlet("/finishtask")
public class FinishTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession().getAttribute("user") != null) {
				int taskId = Integer.parseInt(request.getParameter("taskId"));
				boolean result = false;
				result = ITaskDAO.getDAO(DAOStorageSourse.DATABASE).finishTask(taskId);
				if (result) {
					response.sendRedirect("./dashboard");
					return;
				}

			} else {
				response.sendRedirect("./LogIn");
			}
		} catch (DBException | IOException | EfficientProjectDAOException e) {
			try {
				response.sendRedirect("./error.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
