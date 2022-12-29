package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	public DepartmentDaoJDBC(PreparedStatement st, ResultSet rs) {
		this.st = st;
		this.rs = rs;
	}

	@Override
	public void insert(Department obj) {
		try {
			st = conn.prepareStatement(
				"INSERT INTO department " +
				"(Name) " +
				"VALUES (?) ",
				Statement.RETURN_GENERATED_KEYS
			);
			
			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					obj.setId(rs.getInt(1));
				}
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}

	@Override
	public void update(Department obj) {
		try {
			st = conn.prepareStatement(
				"UPDATE department " +
				"SET Name = ? " +
				"WHERE Id = ? "
			);
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void deleteById(Integer id) {
		try {
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			
			if(rows == 0) {
				throw new DbException("Id not found! No rows affected!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		try {
			st = conn.prepareStatement(
				"SELECT *, department.Name as DepName FROM department WHERE Id = ?"
			);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		try {
			st = conn.prepareStatement(
				"SELECT *, department.Name as DepName " +
				"FROM department " +
				"ORDER BY Name "
			);
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("Id"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("Id"), dep);
				}

				list.add(dep);
			}
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
}
