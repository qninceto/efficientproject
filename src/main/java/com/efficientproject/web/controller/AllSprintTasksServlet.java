package com.efficientproject.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.model.interfaces.ISprintDAO;
import com.efficientproject.model.interfaces.ITaskDAO;
import com.efficientproject.persistance.model.Project;
import com.efficientproject.persistance.model.Sprint;
import com.efficientproject.persistance.model.Task;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class AllSprintTasksServlet
 */
@WebServlet("/allsprinttasks")
public class AllSprintTasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllSprintTasksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			try {
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				boolean projectFinished = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).isProjectFinished(projectId);
				Project project = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(projectId);
				request.setAttribute("project", project);
				Sprint currentSprint = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).getCurrentSprint(projectId);

				if (!projectFinished) {
					if (currentSprint != null) {
						List<Task> tasks = ITaskDAO.getDAO(DAOStorageSourse.DATABASE)
								.getAllTasksFromSprint(currentSprint.getId());
						request.setAttribute("tasks", tasks);
						request.getRequestDispatcher("./workerTasksCurrentSprint.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("./hasNOTCurrentSprint.jsp").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("./projectFinished.jsp").forward(request, response);
				}
			} catch (DBException | EfficientProjectDAOException e) {
				e.printStackTrace();
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
