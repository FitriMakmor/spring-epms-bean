package com.epms.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epms.bean.Employee;
import com.epms.bean.EmployeeManagement;

public class AdminUIClient {

    private static Integer menuInput;
    private static Scanner sc;
    private static EmployeeManagement eM;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        eM = (EmployeeManagement) context.getBean("employeeManagement");//read id
		eM.initReadEmployeeFile();
		eM.initReadAttendanceFile();

        System.out.println("\n\nWelcome to the Payroll Management System");

        while(true) {
            while (true){
                System.out.println("\n-----------------------MAIN MENU-----------------------");
                System.out.println("(1) Employee Management (2) Payment Management (0) Exit");
                menuInput = sc.nextInt();
                if (menuInput < 0 || menuInput > 2) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
                break;
            }
            if (menuInput == 0) {
                break;
            }
            switch (menuInput) {
                case 1 -> employeeManagement();
                case 2 -> paymentManagement();
            }
        }
    }

    public static void employeeManagement() {
        int eMInput;
        while (true){
            System.out.println("\n-------------------------------------------EMPLOYEE MANAGEMENT--------------------------------------------");
            System.out.println("(1) Get List of Employees            (2) Display Employees' Attendance    (3) Get Specific Employee Detail");
            System.out.println("(4) Add a New Employee               (5) Edit an Employee                 (6) Delete an Employee");
            System.out.println("(7) Get Specific Employee Attendance (8) Mark an Employee's Attendance    (0) Exit");
            eMInput = sc.nextInt();
            if(eMInput == 0){
                break;
            }
            switch (eMInput) {
                case 1:
                    displayEmployeeList();
                    break;
                case 2:
                    displayAttendanceList();
                    break;
                case 3:
                    displayEmployeeDetails();
                    break;
                case 4:
                    createNewEmployee();
                    break;
                case 5:
                    selectEmployeeToEdit();
                    break;
                case 6:
                    selectEmployeeToDelete();
                    break;
                case 7:
                    selectEmployeeToViewAttendance();
                    break;
                case 8:
                    selectEmployeeToMarkAttendance();
                    break;
            }
        }
    }

    public static void paymentManagement() {
        int pMInput;
        while (true){
            System.out.println("\n--------------------------------------------PAYMENT MANAGEMENT--------------------------------------------");
            System.out.println("(1) Display Transaction Logs         (2) Display Employee Payrolls        (3) Set Employee's Payroll");
            System.out.println("(4) Delete Employee's Payroll        (5) Execute Employee's Payment       (0) Exit");
            pMInput = sc.nextInt();
            if(pMInput == 0){
                break;
            }
            switch (pMInput) {
                case 1:
                    displayEmployeeTransactionLogs();
                    break;
                case 2:
                    displayEmployeePayrolls();
                    break;
                case 3:
                    selectEmployeeToSetPayroll();
                    break;
                case 4:
                    selectEmployeeToDeletePayroll();
                    break;
                case 5:
                    selectEmployeeToPay();
                    break;
            }
        }
    }

    public static void displayAttendanceList(){
		eM.getAttendanceList();
    }

    public static void displayEmployeeTransactionLogs(){

    }

    public static void displayEmployeeList(){
    	eM.displayListOfEmployee();
    }

    public static void displayEmployeeDetails(){
    	System.out.println("----------------------------");
		System.out.print("Enter Employee Id to search: ");
		int id = sc.nextInt();
		System.out.println("----------------------------");
		if(eM.getEmployee(id) != null) {
			Employee e = eM.getEmployee(id);
			System.out.println(e);
		}else {
			System.out.println("Record not found");
		}
		System.out.println("----------------------------");
    }

    public static void createNewEmployee(){
//    	System.out.print("Enter id: ");
//		int id = sc.nextInt();
//		sc.nextLine();
//		System.out.print("Enter name: ");
//		String name = sc.nextLine();
//		System.out.print("Enter gender(M/F): ");
//		char gender = sc.next().charAt(0);
//
//		eM.addEmployee(id, name, gender);
    	eM.addEmployee();
    }

    public static void displayEmployeePayrolls(){
    	
    }

    public static void selectEmployeeToViewAttendance(){
    	//getSpecific Attendance
    	System.out.print("Enter Employee ID : ");
    	int Id = sc.nextInt();
    	eM.getAttendance(Id);
    }

    public static void selectEmployeeToMarkAttendance(){
    	eM.markAttendance();
    }

    public static void selectEmployeeToDelete(){
		System.out.println(eM.deleteEmployee());
    }

    public static void selectEmployeeToEdit(){
    	if(eM.setEmployee()) {
    		System.out.println("Record is updated successfully");
    		}
    	}

    public static void selectEmployeeToSetPayroll(){

    }

    public static void selectEmployeeToDeletePayroll(){

    }

    public static void selectEmployeeToPay(){

    }

}
