package oldServlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IUserDAO;
import com.efficientproject.persistance.model.User;
import com.efficientproject.util.IntegerChecker;
import com.efficientproject.web.error.DBException;
import com.efficientproject.web.error.EfficientProjectDAOException;

@WebServlet("/ImgOutputServlet")
public class ReadPictureFromFileSysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// public static final String IMAGES_PATH = INFO.IMAGES_PATH;;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.addHeader("Content-Type", "image/jpeg");

			if (request.getSession(false) == null ||request.getSession(false).getAttribute("user") == null) {
				response.sendRedirect("/LogIn");
				return;
			}
			String userIdParam=request.getParameter("userid");
			if ( userIdParam!= null && IntegerChecker.isInteger(userIdParam)) {
				int userId = Integer.parseInt(userIdParam);
				User user = IUserDAO.getDAO(DAOStorageSourse.DATABASE).getUserById(userId);

				String avatarPath = user.getAvatarPath();
				File imgFile = new File(avatarPath);

				try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(imgFile));
						ServletOutputStream fos = response.getOutputStream()) {
					int b = fis.read();
					while (b != -1) {
						fos.write(b);
						b = fis.read();
					}
				}
			} else {
				request.getRequestDispatcher("error2.jsp").forward(request, response);
			}
		} catch (IOException | ServletException | EfficientProjectDAOException | DBException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}

	}

}
