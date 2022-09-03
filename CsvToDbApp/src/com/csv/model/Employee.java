package com.csv.model;

public class Employee {

String employeeName;
String city;
double salary;


public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee(String employeeName, String city, double salary) {
	super();
	this.employeeName = employeeName;
	this.city = city;
	this.salary = salary;
}

public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
@Override
public String toString() {
	return "Employee [employeeName=" + employeeName + ", city=" + city + ", salary=" + salary + "]";
}


}
