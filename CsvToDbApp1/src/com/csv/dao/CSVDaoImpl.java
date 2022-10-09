package com.csv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import com.csv.model.Employee;
import com.csv.util.ModelDAO;

public class CSVDaoImpl implements CSVDao {

	
	
	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		String name=employee.getEmployeeName();
		String city=employee.getCity();
		double salary=employee.getSalary();
		try {
			Connection connection = ModelDAO.openConnection();
			PreparedStatement ps = null;
			
			String sql="insert into employeeDetails1 values(?,?,?)";
			ps=connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, city);
			ps.setDouble(3, salary);
			ps.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//ps.close();
			ModelDAO.closeConnection();
		}
	        
	    
		
		
	}


}
