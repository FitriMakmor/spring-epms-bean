package com.epms.bean;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.XML");
		EmployeeManagement obj = (EmployeeManagement) context.getBean("employeeManagement");//read id

		obj.init();
		//store attendance in arrayList
		obj.init2();
		obj.displayEmployee();
		
		//attendance();
		System.out.println();
		obj.markAttendance();
		System.out.println();
		obj.getAttendanceList();
		System.out.println();
		
		
		//getSpecific Attendance
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Employee ID : ");
	    int Id = input.nextInt();
	    obj.getAttendance(Id);
	}
}
