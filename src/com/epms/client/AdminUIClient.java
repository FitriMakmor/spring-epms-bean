package com.epms.client;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epms.bean.EmployeeManagement;

public class AdminUIClient {

    private static Integer menuInput;
    private static Scanner sc;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        EmployeeManagement employee= (EmployeeManagement) context.getBean("employeeManagementBean");
//        System.out.println(employee.getEmployeeName());
        sc = new Scanner(System.in);


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

    }

    public static void displayEmployeeTransactionLogs(){

    }

    public static void displayEmployeeList(){

    }

    public static void displayEmployeeDetails(){

    }

    public static void createNewEmployee(){

    }

    public static void displayEmployeePayrolls(){

    }

    public static void selectEmployeeToViewAttendance(){

    }

    public static void selectEmployeeToMarkAttendance(){

    }

    public static void selectEmployeeToDelete(){

    }

    public static void selectEmployeeToEdit(){

    }

    public static void selectEmployeeToSetPayroll(){

    }

    public static void selectEmployeeToDeletePayroll(){

    }

    public static void selectEmployeeToPay(){

    }

}
