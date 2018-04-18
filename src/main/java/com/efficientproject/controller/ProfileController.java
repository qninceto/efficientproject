package com.efficientproject.controller;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.efficientproject.model.DAO.INFO;
import com.efficientproject.model.entity.User;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.DAOStorageSourse;
import com.efficientproject.model.interfaces.IUserDAO;
import com.efficientproject.util.CredentialsChecks;
import com.efficientproject.util.Encrypter;

@MultipartConfig(maxFileSize = 16177215) 
@Controller
@RequestMapping(value="/profile-edit")
//ex ProfileEditServlet!
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String IMAGES_PATH = INFO.IMAGES_PATH;

	private static final DAOStorageSourse SOURCE_DATABASE = DAOStorageSourse.DATABASE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");

			if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null) {
				response.sendRedirect("./LogIn");
				return;
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setHeader("Expires", "0"); // Proxies.

			request.getRequestDispatcher("./profileEdit.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null) {
				response.sendRedirect("./LogIn");
				return;
			}
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./profileEdit.jsp");
			
			User user = (User) request.getSession().getAttribute("user");
			int userId = user.getId();
			
			String firstNameParam = request.getParameter("first-name");
			String lastNameParam = request.getParameter("last-name");
			String emailParam = request.getParameter("email");
			String oldPassParam = request.getParameter("old-password");
			String newPassParam = request.getParameter("new-password");
			String reNewPassParam = request.getParameter("rep-new-password");
			
			if(firstNameParam == null  && lastNameParam == null && emailParam == null && oldPassParam == null && newPassParam == null && reNewPassParam == null) {
				request.getRequestDispatcher("error2.jsp").forward(request, response);
				return;
			}
			String firstName = escapeHtml4(firstNameParam).trim();
			String lastName = escapeHtml4(lastNameParam).trim();
			String email = escapeHtml4(emailParam).trim();
			String oldPass = escapeHtml4(oldPassParam);
			String newPass = escapeHtml4(newPassParam);
			String reNewPass = escapeHtml4(reNewPassParam);
			Part avatarPart = request.getPart("avatar");
			
			boolean firstNameNotEmptyandDifferent = firstName.length() > 0 && !firstName.equals(user.getFirstName());
			boolean lastNameNotEmptyAndDifferent = lastName.length() > 0 && !lastName.equals(user.getLastName());
			boolean emailNotEmptyAndDifferent = email.length() > 0 && !email.equals(user.getEmail());
			boolean newPassNotEmpty = newPass.trim().length() > 0;
			boolean oldPassNotEmpty = oldPass.trim().length() > 0;
			// check if there is actually uploaded file:
			boolean avatarPartNotEmpty = avatarPart.getSize() != 0;
			if (avatarPartNotEmpty) {
				String avatarPath = IMAGES_PATH + File.separator + userId + ".jpg";
				File myFile = new File(avatarPath);
				
				if (!myFile.exists()) {
					myFile.createNewFile();
				}
				try (BufferedInputStream fis = new BufferedInputStream(avatarPart.getInputStream());
						BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(myFile))) {
					int b = fis.read();
					while (b != -1) {
						fos.write(b);
						b = fis.read();
					}
				}
				user.setAvatarPath(avatarPath);
			}



			if (firstNameNotEmptyandDifferent) {
				user.setFirstName(firstName);
			}

			if (lastNameNotEmptyAndDifferent) {
				user.setLastName(lastName);
			}

			if (emailNotEmptyAndDifferent) {
				if (!CredentialsChecks.isMailValid(email)) {
					forwardWithErrorMessage(request, response, dispatcher, "Invalid e-mail! Try Again!");
					return;
				}
				if (IUserDAO.getDAO(SOURCE_DATABASE).isThereSuchAUser(email)) {
					forwardWithErrorMessage(request, response, dispatcher,"User with this email already exists, use another email!");
					return;
				}
				user.setEmail(email);
			}

			if (newPassNotEmpty) {
				if (!oldPassNotEmpty) {
					forwardWithErrorMessage(request, response, dispatcher,"You cannot change the password without confirming the old one!");
					return;
				}
				if (oldPassNotEmpty && !Encrypter.encrypt(oldPass).equals(user.getPassword())) {
					forwardWithErrorMessage(request, response, dispatcher, "You entered wrong old password,try again!");
					return;
				}
				if (!newPass.equals(reNewPass)) {
					forwardWithErrorMessage(request, response, dispatcher,"New passwords do no match, please make sure they do!");
					return;
				}
				if (!CredentialsChecks.isPaswordStrong(newPass)) {
					forwardWithErrorMessage(request, response, dispatcher,"New password must contain 5 symbols and at least one digit and letter!");
					return;
				}
				user.setPassword(Encrypter.encrypt(newPass));
			}

			if (firstNameNotEmptyandDifferent || lastNameNotEmptyAndDifferent || emailNotEmptyAndDifferent
					|| newPassNotEmpty || avatarPartNotEmpty) {
				System.out.println("change done");
				IUserDAO.getDAO(SOURCE_DATABASE).updateUsersDetails(user);
				request.getSession().setAttribute("user", user);
			}
			response.sendRedirect("./Profile");
		} catch (ServletException | IOException | DBException | EfficientProjectDAOException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IOException | ServletException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void forwardWithErrorMessage(HttpServletRequest request, HttpServletResponse response,
			RequestDispatcher dispatcher, String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		dispatcher.forward(request, response);
	}

}
