package com.efficientproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.entity.Sprint;
import com.efficientproject.model.entity.Task;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.model.interfaces.ISprintDAO;
import com.efficientproject.model.interfaces.ITaskDAO;

@WebServlet("/allTasksProject")
public class AllTasksProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			if (request.getSession().getAttribute("user") != null) {

				int projectId = Integer.parseInt(request.getParameter("projectId"));
				boolean projectFinished = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).isProjectFinished(projectId);
				int backLog = Integer.parseInt(request.getParameter("backLog"));
				request.setAttribute("backLog", backLog);
				request.setAttribute("project",
						IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(projectId));

				List<Task> tasks = null;
				if (backLog == 1) {
					tasks = ITaskDAO.getDAO(DAOStorageSourse.DATABASE).getProjectBackLog(projectId);
					Sprint currentSprint = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).getCurrentSprint(projectId);
					if (currentSprint != null) {
						request.setAttribute("sprintId", currentSprint.getId());
					}
				} else {
					tasks = ITaskDAO.getDAO(DAOStorageSourse.DATABASE).getAllTasksOfProject(projectId);
				}
				if (tasks != null) {
					if (projectFinished && backLog == 1) {
						request.getRequestDispatcher("./projectFinished.jsp").forward(request, response);
						return;
					}

					request.setAttribute("tasks", tasks);

					request.getRequestDispatcher("./allProjectTasks.jsp").forward(request, response);
				} else {
					redirecttoerrorpage(response);
				}
			} else {
				redirecttoerrorpage(response);
			}
		} catch (EfficientProjectDAOException | DBException | ServletException | IOException e) {
			redirecttoerrorpage(response, e);
		}
	}

	private void redirecttoerrorpage(HttpServletResponse response, Exception e) {
		try {
			response.sendRedirect("error.jsp");
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void redirecttoerrorpage(HttpServletResponse response) {
		try {
			response.sendRedirect("error.jsp");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
