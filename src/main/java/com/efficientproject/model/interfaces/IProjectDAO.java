package com.efficientproject.model.interfaces;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import com.efficientproject.model.DAO.ProjectDAO;
import com.efficientproject.model.POJO.Project;
import com.efficientproject.model.POJO.User;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;



public interface IProjectDAO {
		public static ProjectDAO getDAO(DAOStorageSourse storage) throws UnsupportedDataTypeException {
			if (storage.equals(DAOStorageSourse.DATABASE)) {
				return new ProjectDAO();
			}
			throw new UnsupportedDataTypeException();
		}


		Project getProjectByID(int projectId) throws DBException, EfficientProjectDAOException, UnsupportedDataTypeException;

		List<Project> getAllProjectsFromOrganization(int organizationId)
				throws DBException, UnsupportedDataTypeException, EfficientProjectDAOException;

		List<User> getAllWorkersWorkingOnAProject(int projectId)
				throws DBException, UnsupportedDataTypeException, EfficientProjectDAOException;

		int addProject(Project project, int adminId) throws EfficientProjectDAOException, DBException;


		boolean isThisProjectOfThisUser(int projectId, int userId) throws DBException;


		boolean isProjectFinished(int projectId) throws EfficientProjectDAOException, DBException;

		
}