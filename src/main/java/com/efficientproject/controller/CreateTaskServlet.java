package com.efficientproject.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.entity.Epic;
import com.efficientproject.model.entity.Task;
import com.efficientproject.model.entity.Type;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IEpicDAO;
import com.efficientproject.model.interfaces.ITaskDAO;
import com.efficientproject.model.interfaces.ITypeDAO;

/**
 * Servlet implementation class CreateTaskServlet
 */
@WebServlet("/createtask")
public class CreateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession() != null && request.getSession().getAttribute("user") != null) {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			try {
				List<Epic> epics = IEpicDAO.getDAO(DAOStorageSourse.DATABASE).getAllEpicsByProject(projectId);
				List<Type> types = ITypeDAO.getDAO(DAOStorageSourse.DATABASE).getAllTypes();
				
				request.setAttribute("epics", epics);
				request.setAttribute("types", types);
				
				response.setCharacterEncoding("utf-8");
			} catch (DBException | EfficientProjectDAOException e) {
				e.printStackTrace();
				
				response.sendRedirect("./error.jsp");
				
				return;
			} 
			request.setAttribute("projectId", projectId);
			RequestDispatcher rd = request.getRequestDispatcher("./createTask.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("./LogIn");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession() != null && request.getSession().getAttribute("user") != null) {
			User reporter = (User) request.getSession().getAttribute("user");
			String summary = request.getParameter("summary");
			summary = URLEncoder.encode(summary, "ISO-8859-1");
			summary = URLDecoder.decode(summary, "UTF-8");
			String description = request.getParameter("description");
			description = URLEncoder.encode(description, "ISO-8859-1");
			description = URLDecoder.decode(description, "UTF-8");
			float estimate = Float.parseFloat(request.getParameter("estimate"));
			int typeId = Integer.parseInt(request.getParameter("types"));
			int epicId = Integer.parseInt(request.getParameter("epics"));
			
			try {
				Type type = ITypeDAO.getDAO(DAOStorageSourse.DATABASE).getTypeById(typeId);
				Epic epic = IEpicDAO.getDAO(DAOStorageSourse.DATABASE).getEpicById(epicId);
				
				Task tskToAdd = new Task(type, summary, description, estimate, reporter, epic);
				
				int id = ITaskDAO.getDAO(DAOStorageSourse.DATABASE).addTask(tskToAdd);
				User user=(User) request.getSession().getAttribute("user");
				int projectId=epic.getProject().getId();
				if(user.isAdmin()) {
					response.sendRedirect("./projectdetail?projectId="+projectId);
				}else {
					response.sendRedirect("./createtask?projectId="+projectId);
				}
				
				
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EfficientProjectDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./LogIn");
		}
	}
}
