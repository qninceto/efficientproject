package oldServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IUserDAO;
import com.efficientproject.persistance.model.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class returnUnemployedWorkersS
 */
@WebServlet("/returnUnemployedWorkers")
public class ReturnUnemployedWorkersS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnUnemployedWorkersS() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");

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
				String s = new Gson().toJson(IUserDAO.getDAO(DAOStorageSourse.DATABASE).getAllUnemployedWorkers());
				response.getWriter().println(s);
		} catch (IOException | ServletException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}

}
