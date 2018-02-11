package com.efficientproject.model.interfaces;

import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import com.efficientproject.model.DAO.SprintDAO;
import com.efficientproject.model.POJO.Sprint;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;

public interface ISprintDAO {
	public static SprintDAO getDAO(DAOStorageSourse storage) throws UnsupportedDataTypeException {
		if (storage.equals(DAOStorageSourse.DATABASE)) {
			return new SprintDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	Sprint getCurrentSprint(int projectId) throws DBException, EfficientProjectDAOException;

	Sprint getSprintBId(int sprintId) throws DBException, EfficientProjectDAOException;

	int createSprint(Sprint sprint) throws DBException, EfficientProjectDAOException;

	Map<String, Integer> tasksPerSprints(int projectId) throws EfficientProjectDAOException, DBException;
}
