package oldServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IProjectDAO;
import com.efficientproject.persistance.model.Project;
import com.efficientproject.persistance.model.User;
import com.efficientproject.util.IntegerChecker;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

/**
 * Servlet implementation class returnUnemployedWorkersS
 */
@WebServlet("/burnDownChartviewer")
public class BurnDownChartServletViewer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");

			if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
				response.sendRedirect("./LogIn");
				return;
			}
			String projectIdParam = request.getParameter("projectId");
			if (projectIdParam != null && IntegerChecker.isInteger(projectIdParam)) {
				User user = (User) request.getSession().getAttribute("user");

				if (!user.isAdmin()) {
					request.getRequestDispatcher("errorNotAuthorized.jsp").forward(request, response);
					return;
				}
				int projectId = Integer.parseInt(projectIdParam);
				request.setAttribute("projectId", projectId);
				Project currentProject = IProjectDAO.getDAO(DAOStorageSourse.DATABASE).getProjectByID(projectId);
				request.setAttribute("project", currentProject);
				request.getRequestDispatcher("burnDownChart.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("error2.jsp").forward(request, response);
			}
		} catch (IOException | ServletException | DBException | EfficientProjectDAOException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}

}
