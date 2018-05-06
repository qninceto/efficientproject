package com.efficientproject.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IEpicDAO;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.persistance.model.Epic;
import com.efficientproject.persistance.model.Project;
import com.efficientproject.persistance.model.User;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class CreateEpicServlet
 */
@WebServlet("/createepic")
public class CreateEpicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateEpicServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession() != null && request.getSession().getAttribute("user") != null) {
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				request.setAttribute("projectId", projectId);
				List<Project> projects = new ArrayList<>();
				User user = (User) request.getSession().getAttribute("user");

				projects = IProjectDAO.getDAO(DAOStorageSourse.DATABASE)
						.getAllProjectsFromOrganization(user.getOrganization().getId());
				request.setAttribute("projects", projects);
				RequestDispatcher rd = request.getRequestDispatcher("./createEpic.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./LogIn");
			}
		} catch (EfficientProjectDAOException | DBException | ServletException | IOException e) {
			try {
				response.sendRedirect("error.jsp");
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession() != null && request.getSession().getAttribute("user") != null) {
				String name = request.getParameter("name");
				name = URLEncoder.encode(name, "ISO-8859-1");
				name = URLDecoder.decode(name, "UTF-8");
				int estimate = Integer.parseInt(request.getParameter("estimate"));
				String description = request.getParameter("description");
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				// int projectId = Integer.parseInt(request.getParameter("projects"));

				Project project = null;

				project = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(projectId);
				Epic epicToAdd = new Epic(name, estimate, description, project);
				int id = IEpicDAO.getDAO(DAOStorageSourse.DATABASE).createEpic(epicToAdd);
				
				request.setCharacterEncoding("utf-8");

				response.setCharacterEncoding("utf-8");
				response.sendRedirect("./projectdetail?projectId=" + project.getId());
			} else {

			}
		} catch (EfficientProjectDAOException | IOException | DBException e) {
			try {
				response.sendRedirect("error.jsp");
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
}