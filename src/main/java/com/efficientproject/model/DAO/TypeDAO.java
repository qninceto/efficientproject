package com.efficientproject.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.efficientproject.model.POJO.Type;
import com.efficientproject.model.exceptions.DBException;
import com.efficientproject.model.exceptions.EfficientProjectDAOException;
import com.efficientproject.model.interfaces.ITypeDAO;

public class TypeDAO extends AbstractDBConnDAO implements ITypeDAO{
	private static final String GET_ALL_TYPES = "SELECT * from types;";
	private static final String GET_TYPE_BY_ID = "SELECT * from types where id=?;";
	
	@Override
	public Type getTypeById(int TypeId) throws DBException, EfficientProjectDAOException {
		if (TypeId <0) {
			throw new EfficientProjectDAOException("Invalid input");
		}
		Type type = null;
		try {
			PreparedStatement ps = getCon().prepareStatement(GET_TYPE_BY_ID);
			ps.setInt(1, TypeId);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				type = new Type(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			throw new DBException("can not find this type",e);
		}
		return type;
	}

	@Override
	public List<Type> getAllTypes() throws DBException {
		List<Type> types = new ArrayList<>();
		try {
			PreparedStatement ps = getCon().prepareStatement(GET_ALL_TYPES);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				types.add(new Type(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			throw new DBException("can not find Types",e);
		}
		return types;
	}
}
