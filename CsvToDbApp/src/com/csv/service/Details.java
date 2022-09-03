package com.csv.service;

import com.csv.model.Employee;

public class Details {
public static Employee createDetails(String[] metadata) {
	String name = metadata[1]; 
	String city = metadata[2]; 
	double salary = Integer.parseInt(metadata[3]); 
	return new Employee(name,city,salary);
}


}
