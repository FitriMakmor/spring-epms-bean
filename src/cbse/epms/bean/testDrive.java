package cbse.epms.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testDrive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		
//		
//		EmployeeManagement2 emp = (EmployeeManagement2)context.getBean("employeeManagement2");
//		List<Employee2> list = new ArrayList<Employee2>();
//		
//		Scanner s = new Scanner(System.in);
//		Scanner s2 = new Scanner(System.in);
//		int choice;
//		
//		do {
//			System.out.println("1. ADD");
//			System.out.println("2. EDIT");
//			System.out.println("3. DELETE");
//			System.out.println("4. DISPLAY");
//			System.out.println("5. SEARCH");
//			System.out.println("Enter 0 to exit");
//			System.out.print("\nEnter Your Choice (1-5): ");
//			
//			choice = s.nextInt();
//			
//			switch(choice) {
//				case 1:
//					
//					System.out.print("Enter id: ");
//					int id = s.nextInt();
//					System.out.print("Enter name: ");
//					String name = s2.nextLine();
//					System.out.print("Enter gender(M/F): ");
//					String gender = s2.nextLine();
//					
//					emp.addEmployee(id, name, gender);
//
//				break;
//				case 2:
//					System.out.println("----------------------------");
//					System.out.print("Enter Employee2 Id to update: ");
//					id = s.nextInt();
//					System.out.println("----------------------------");
//					if(emp.getEmployee(id) != null) {
//						System.out.print("Enter name: ");
//						name = s2.nextLine();
//						System.out.print("Enter gender(M/F): ");
//						gender = s2.nextLine();
//						
//						if(emp.setEmployee(id, name, gender)) {
//							System.out.println("Record is updated successfully");
//						}
//					}else {
//						System.out.println("Record not found");
//					}
//					System.out.println("----------------------------");
//				break;
//				case 3:
//					System.out.println("----------------------------");
//					System.out.print("Enter Employee2 Id to delete: ");
//					id = s.nextInt();
//					System.out.println("----------------------------");
//					if(emp.deleteEmployee(id)) {
//						System.out.println("Record is deleted successfully");
//					}else {
//						System.out.println("Record not found");
//					}
//					System.out.println("----------------------------");
//				break;
//				case 4: 
//					System.out.println("----------------------------");
//					list = emp.getEmployeeList();
//					Iterator<Employee2> i  = list.iterator();
//					while(i.hasNext()) {
//						System.out.println(i.next());
//					}
//					System.out.println("----------------------------");
//				break;
//				case 5:
//					System.out.println("----------------------------");
//					System.out.print("Enter Employee2 Id to search: ");
//					id = s.nextInt();
//					System.out.println("----------------------------");
//					if(emp.getEmployee(id) != null) {
//						Employee2 e = emp.getEmployee(id);
//						System.out.println(e);
//					}else {
//						System.out.println("Record not found");
//					}
//					System.out.println("----------------------------");
//				break;
//			}
//		}while(choice!=0);
	}

}
