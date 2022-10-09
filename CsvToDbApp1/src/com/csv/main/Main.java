package com.csv.main;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;

import java.util.List;

import com.csv.dao.CSVDaoImpl;
import com.csv.model.Employee;
import com.csv.service.*;
import com.csv.util.ModelDAO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Welcome to Employee Database");

		 Employee employeeObject=new Employee();
//		 CSVDaoImpl newdetails=new CSVDaoImpl();
		 CSVServiceImpl newdetails=new CSVServiceImpl();
		 Connection connection=ModelDAO.openConnection();
		 PreparedStatement ps=null;
		 String sql="insert into employeeDetails1 values(?,?,?)";
		 //String sql="create table employeedetails1(name varchar(10),city varchar(10),salary int)";
		 try {
			ps=connection.prepareStatement(sql);
			System.out.println("1.Reading data from CSV file \t2.Converting Object to MySql \t3.Direct Input  from the user");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:System.out.println("Reading the Data from the CSV file......");
			 List<Employee> employeeList = new ArrayList<>();
			 String file="C:\\Users\\MathanRajS\\Downloads\\Employee.csv";
			 Path pathToFile = Paths.get(file);
			 try {
	 			BufferedReader br = Files.newBufferedReader(pathToFile);
				String line = br.readLine();
				Details details=new Details();
				while (line != null) {
					String[] attributes = line.split(",");
					employeeObject=details.createDetails(attributes);
					//employeeList.add(employeeObject);
					newdetails.saveToDB(employeeObject);
					line = br.readLine();
				}
				System.out.println("Data transfered from CSV file to MySql Server");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
//			 System.out.println("Printing the data from CSV file .....");
//			 for(Employee e:employeeList) {
//				 System.out.println(e);
//			 }
			 //newdetails.saveToDB(employeeObject);
			 break;
			
			}//switch ending....
			
			
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		
		 
		 finally {
			try {
				ps.close();
				ModelDAO.closeConnection(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }


	}

}
