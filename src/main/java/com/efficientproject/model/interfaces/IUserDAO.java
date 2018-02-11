package com.efficientproject.model.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import javax.activation.UnsupportedDataTypeException;

import com.efficientproject.model.DAO.UserDAO;
import com.efficientproject.model.POJO.User;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;

public interface IUserDAO {

	public static UserDAO getDAO(DAOStorageSourse storage) throws UnsupportedDataTypeException {
		if (storage.equals(DAOStorageSourse.DATABASE)) {
			return new UserDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	User getUserById(int userID) throws UnsupportedDataTypeException, EfficientProjectDAOException, DBException;

	User getUserByEmail(String email) throws UnsupportedDataTypeException, EfficientProjectDAOException, DBException;

	int addUserAdmin(User user) throws EfficientProjectDAOException, DBException, UnsupportedDataTypeException, SQLException;

	int addUserWorker(User user) throws EfficientProjectDAOException, DBException, UnsupportedDataTypeException;

	boolean isThereSuchAUser(String email) throws DBException, EfficientProjectDAOException;

	boolean updateUsersDetails(User user) throws DBException, EfficientProjectDAOException;

	boolean unemployWorker(User user) throws DBException, EfficientProjectDAOException;

	boolean addWorkerToProject(int userId, int projectId)
			throws UnsupportedDataTypeException, EfficientProjectDAOException, DBException, SQLException;

	int returnCurrentWorkersProject(User user) throws EfficientProjectDAOException, DBException;

	boolean removeWorkerFromUnemployedWorkers(User worker) throws EfficientProjectDAOException;

	boolean addWorkerToUnemployedWorkers(User worker) throws EfficientProjectDAOException;

	Collection<User> getAllUnemployedWorkers();

	boolean isThereCurrentProjectForThisWorker(User user) throws EfficientProjectDAOException, DBException;

	
}
