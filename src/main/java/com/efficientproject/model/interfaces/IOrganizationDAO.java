package com.efficientproject.model.interfaces;

import javax.activation.UnsupportedDataTypeException;

import com.efficientproject.model.DAO.OrganizationDAO;
import com.efficientproject.model.entity.Organization;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;



public interface IOrganizationDAO {
	public static OrganizationDAO getDAO(DAOStorageSourse storage) throws UnsupportedDataTypeException {
		if (storage.equals(DAOStorageSourse.DATABASE)) {
			return new OrganizationDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	Organization getOrgById(int orgId) throws EfficientProjectDAOException, DBException, UnsupportedDataTypeException;

	int addOrganization(Organization organization) throws EfficientProjectDAOException, DBException;

	boolean isThereSuchOrganization(String name) throws EfficientProjectDAOException, DBException;
}
