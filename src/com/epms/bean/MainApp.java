package com.epms.bean;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.XML");
		EmployeeManagement obj = (EmployeeManagement) context.getBean("employeeManagement");//read id

	
		
		obj.initReadEmployeeFile();
		obj.initReadAttendanceFile();
		
//		//displayEmployee
//		System.out.println("getEmployees()");
//		obj.displayListOfEmployee();
//		
////		System.out.println("------------------------------\n");
////		//addEmployee
////		System.out.println("Add Employee");
////		obj.addEmployee();
////		System.out.println("AfterAdd Employee");
////		obj.displayListOfEmployee();
//		
////		System.out.println("------------------------------\n");
//////		editEmployee
////		System.out.println("Before Update");
////		obj.displayListOfEmployee();
////		obj.setEmployee();
////		System.out.println("After Update");		
////		obj.displayListOfEmployee();
//		
//		
////		//delete Employee Testing
////		System.out.println("------------------------------\n");
////		System.out.println("Before Delete");
////		obj.displayListOfEmployee();
////		System.out.println(obj.deleteEmployee());
////		System.out.println("After Delete");
////		obj.displayListOfEmployee();
//		
//		//get Employee Details()
//		
	
//		obj.getAttendanceList();
//		
//		//attendance();
//		System.out.println();
//		obj.markAttendance();
//		System.out.println();
//		obj.getAttendanceList();
//		System.out.println();
////		
//		
//		//getSpecific Attendance
//		Scanner input = new Scanner(System.in);
//		System.out.print("Enter Employee ID : ");
//	    int Id = input.nextInt();
//	    obj.getAttendance(Id);
	}
}