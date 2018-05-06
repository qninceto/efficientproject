package com.efficientproject.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IEpicDAO;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.model.interfaces.ISprintDAO;
import com.efficientproject.persistance.model.Epic;
import com.efficientproject.persistance.model.Project;
import com.efficientproject.persistance.model.Sprint;
import com.efficientproject.persistance.model.User;
import com.efficientproject.persistance.model.Task.TaskState;
import com.efficientproject.util.IntegerChecker;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class projectDetail
 */
@WebServlet("/projectdetail")
public class ProjectDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			if (request.getSession(false) == null || request.getSession(false).getAttribute("user")==null) {
				response.sendRedirect("./LogIn");
				return;
			}
			String projectIdParam=request.getParameter("projectId");
			if ( projectIdParam!= null && IntegerChecker.isInteger(projectIdParam)) {
				User user = (User) request.getSession().getAttribute("user");

				if (!user.isAdmin()) {
					request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
					return;
				}
				int projectId = Integer.parseInt(projectIdParam);
				if (!IProjectDAO.getDAO(SOURCE_DATABASE).isThisProjectOfThisUser(projectId, user.getId())) {
					request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
					return;
				}
				Sprint currentSprint = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).getCurrentSprint(projectId);
				if (currentSprint != null) {
					request.setAttribute("currentSprint", currentSprint);
				}

				boolean projectFinished=IProjectDAO.getDAO(DAOStorageSourse.DATABASE).isProjectFinished(projectId);
				request.setAttribute("projectFinished", projectFinished);
				Map<TaskState, Integer> tasksNumberPerState=IProjectDAO.getDAO(SOURCE_DATABASE).tasksNumberPerState(projectId);
				request.setAttribute("tasksOpen", tasksNumberPerState.get(TaskState.OPEN));
				request.setAttribute("tasksDone", tasksNumberPerState.get(TaskState.RESOLVED));
				request.setAttribute("tasksInProgress", tasksNumberPerState.get(TaskState.INPROGRESS));
			
				Project currentProject = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(projectId);
				request.setAttribute("project", currentProject);

				List<Epic> epics = IEpicDAO.getDAO(DAOStorageSourse.DATABASE).getAllEpicsByProject(projectId);
				request.setAttribute("epics", epics);

				List<User> users = IProjectDAO.getDAO(DAOStorageSourse.DATABASE)
						.getAllWorkersWorkingOnAProject(projectId);
				request.setAttribute("workers", users);
				
				RequestDispatcher rd = request.getRequestDispatcher("/projectDetail.jsp");
				rd.forward(request, response);
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
