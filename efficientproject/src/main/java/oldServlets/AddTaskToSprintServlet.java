package oldServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.model.interfaces.ITaskDAO;
import com.efficientproject.persistance.model.User;
import com.efficientproject.util.IntegerChecker;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class AddTaskToSprintServlet
 */
@WebServlet("/addTaskToSprint")

public class AddTaskToSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			/**
			 * check if there is no session or there is but the user is different or the
			 * user is not admin:
			 */
			if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
				response.sendRedirect("./LogIn");
				return;
			}
			User user = (User) request.getSession().getAttribute("user");

			if (!user.isAdmin()) {
				request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
				return;
			}

			String taskIdParam = request.getParameter("taskId");
			String sprintIdParam = request.getParameter("sprintId");
			String projectIdParam = request.getParameter("projectId");
			if (taskIdParam != null && sprintIdParam != null && projectIdParam != null
					&& IntegerChecker.isInteger(projectIdParam) && IntegerChecker.isInteger(sprintIdParam)
					&& IntegerChecker.isInteger(taskIdParam)) {

				int projectId = Integer.parseInt(projectIdParam);
				/**
				 * check if the project is of this admin
				 */
				if (!IProjectDAO.getDAO(SOURCE_DATABASE).isThisProjectOfThisUser(projectId, user.getId())) {
					request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
					return;
				}
				int taskId = Integer.parseInt(taskIdParam);
				int sprintId = Integer.parseInt(sprintIdParam);

				ITaskDAO.getDAO(SOURCE_DATABASE).addTaskToSprint(taskId, sprintId);
				response.sendRedirect("./allTasksProject?projectId=" + projectId + "&backLog=1");
			} else {
				request.getRequestDispatcher("error2.jsp").forward(request, response);
			}

		} catch (DBException | EfficientProjectDAOException | IOException | ServletException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}

	}

}
