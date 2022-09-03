package com.csv.service;


import com.csv.dao.CSVDaoImpl;
import com.csv.model.Employee;

public class CSVServiceImpl implements CSVService {

//	List<Employee> employeeList1=new ArrayList<>();
	CSVDaoImpl enterDetails=new CSVDaoImpl();
	@Override
	public void saveToDB(Employee employee) {
		// TODO Auto-generated method stub
//		employeeList1.add(employee);
		enterDetails.save(employee);
		System.out.println("Saved.....");
		
		 System.out.println("Printing the data from CSV file .....");
		 System.out.println(employee);
		
	}



}
