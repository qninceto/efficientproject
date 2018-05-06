package com.efficientproject.web.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.ITaskDAO;
import com.efficientproject.persistance.model.Project;
import com.efficientproject.persistance.model.User;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class AssigneTaskFromSprintServlet
 */
@WebServlet("/assignetask")
public class AssigneTaskFromSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession(false) == null) {
				response.sendRedirect("./LogIn");
				return;
			}
			User user = (User) request.getSession().getAttribute("user");

			if (user.isAdmin()) {
				response.sendRedirect("./LogIn");
				return;
			}

				int taskId = Integer.parseInt(request.getParameter("taskId"));
				if(!ITaskDAO.getDAO(DAOStorageSourse.DATABASE).assignTask(taskId, user.getId())) {
					//TODO  task already assiged --->refresh,ajax
				}
				Project project=(Project) request.getSession().getAttribute("project");
				response.sendRedirect("allsprinttasks?projectId="+project.getId());
		} catch (EfficientProjectDAOException | IOException | DBException e) {
			try {
				response.sendRedirect("error.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
