package com.efficientproject.model.interfaces;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import com.efficientproject.model.DAO.EpicDAO;
import com.efficientproject.model.entity.Epic;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;



public interface IEpicDAO {
	public static EpicDAO getDAO(DAOStorageSourse storage) throws UnsupportedDataTypeException {
		if (storage.equals(DAOStorageSourse.DATABASE)) {
			return new EpicDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	int createEpic(Epic epic) throws EfficientProjectDAOException, DBException;

	List<Epic> getAllEpicsByProject(int projectId) throws DBException, UnsupportedDataTypeException, EfficientProjectDAOException;

	Epic getEpicById(int epicId) throws UnsupportedDataTypeException, DBException, EfficientProjectDAOException;
}
