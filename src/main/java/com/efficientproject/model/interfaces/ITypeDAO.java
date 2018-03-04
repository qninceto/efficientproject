package com.efficientproject.model.interfaces;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import com.efficientproject.model.DAO.TypeDAO;
import com.efficientproject.model.entity.Type;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;

public interface ITypeDAO {
	public static TypeDAO getDAO(DAOStorageSourse storage) throws UnsupportedDataTypeException {
		if (storage.equals(DAOStorageSourse.DATABASE)) {
			return new TypeDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	Type getTypeById(int TypeId) throws DBException, EfficientProjectDAOException;

	List<Type> getAllTypes() throws DBException;
}
