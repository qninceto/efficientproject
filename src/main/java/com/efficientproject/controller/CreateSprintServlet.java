
package com.efficientproject.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.entity.Project;
import com.efficientproject.model.entity.Sprint;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.model.interfaces.ISprintDAO;

/**
 * Servlet implementation class CreateSprintServlet
 */
@WebServlet("/createsprint")
public class CreateSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession().getAttribute("user") != null) {
				Sprint currentSprint = null;
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				int all = Integer.parseInt(request.getParameter("all"));
				request.setAttribute("all", all);
				currentSprint = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).getCurrentSprint(projectId);
				Project project = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(projectId);
				if (currentSprint == null) {
					request.setAttribute("projectId", projectId);
					request.setAttribute("project", project);
					request.getRequestDispatcher("./createSprint.jsp").forward(request, response);
				} else {
					response.sendRedirect("./hasCurrentSprint.jsp");
				}

			} else {
				response.sendRedirect("./LogIn");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession() != null && request.getSession().getAttribute("user") != null) {
				String name = request.getParameter("name");
				name = URLEncoder.encode(name, "ISO-8859-1");
				name = URLDecoder.decode(name, "UTF-8");
				int duration = Integer.parseInt(request.getParameter("duration"));
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				Date startDate = new Date(System.currentTimeMillis());
				Sprint sprintToAdd = new Sprint(name,startDate, duration, projectId);
				
				int id = ISprintDAO.getDAO(DAOStorageSourse.DATABASE).createSprint(sprintToAdd);
				
				response.sendRedirect("./projectdetail?projectId=" + projectId);
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
