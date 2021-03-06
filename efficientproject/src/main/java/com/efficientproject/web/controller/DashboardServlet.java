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
import com.efficientproject.model.interfaces.IUserDAO;
import com.efficientproject.persistance.model.Project;
import com.efficientproject.persistance.model.User;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

				
	
			//doGet


			User user = (User) request.getSession().getAttribute("user");
			// if the user is admin forward to admins page
			if (user.isAdmin()) {
				int intorganizationId = user.getOrganization().getId();
				List<Project> projects = IProjectDAO.getDAO(DAOStorageSourse.DATABASE)
						.getAllProjectsFromOrganization(intorganizationId);
				if (projects != null) {
					request.setAttribute("projects", projects);
				} else {
					request.getRequestDispatcher("error2.jsp").forward(request, response);
				}

				String organizationName = user.getOrganization().getName();
				request.setAttribute("organizationName", organizationName);

				/*request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.getRequestDispatcher("./homePageAdmin.jsp").forward(request, response);
				response.setContentType("text/html");*/
			} else {// if the user is worker:
				if (IUserDAO.getDAO(DAOStorageSourse.DATABASE).isThereCurrentProjectForThisWorker(user)) {
					int CurrentProjectId = IUserDAO.getDAO(DAOStorageSourse.DATABASE).returnCurrentWorkersProject(user);
					Project project = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(CurrentProjectId);
					request.getSession().setAttribute("project", project);
				}
				response.sendRedirect("./workertasks");
			}
		}
	}

}
