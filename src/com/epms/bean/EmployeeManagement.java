package com.epms.bean;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;

public class EmployeeManagement{
	   
    ArrayList<Attendance> arrayOfAttendance= new ArrayList<>();
    ArrayList<Attendance> arrayOfMarkAttendance = new ArrayList<>();
    ArrayList<Employee> arrayOfEmployee= new ArrayList<>();
    
	FileInputStream employeeFile = null;
	FileInputStream attendanceFile = null;
	
	Scanner input = new Scanner(System.in);
	
	public void init() {
		try {
			    employeeFile = new FileInputStream("employee.txt");     
				Scanner sc=new Scanner(employeeFile); 
				while(sc.hasNextLine())  {
					String line = sc.nextLine();
					String[] words=line.split(",");
					arrayOfEmployee.add(new Employee(Integer.parseInt(words[0]),words[1],words[2].charAt(0)));
				} 
				
				sc.close();     //closes the scanner  
		}  
		catch(IOException e)  {  
		e.printStackTrace();  
		}  
	}
	
	public void init2() {
		try {
		    attendanceFile = new FileInputStream("attendance.txt");     
			Scanner sc=new Scanner(attendanceFile); 
			while(sc.hasNextLine())  {
				String line = sc.nextLine();
				if ( line.trim().length() == 0 ) {
				    continue;  // Skip blank lines
				}
				
				String parts1 = line.replace(":",",");
				String[] words=parts1.split(",");
				boolean isPresent=Boolean.parseBoolean(words[2]);
				arrayOfAttendance.add(new Attendance(words[0],Integer.parseInt(words[1]),isPresent));
			
			} 
			sc.close();     //closes the scanner  
		}  
		catch(IOException e)  {  
			e.printStackTrace();  
		} 
	}
	
	public void displayEmployee(){
		for(Employee str: arrayOfEmployee){
			System.out.println("employeeId: "+str.getEmployeeId()+", name: " + str.getName() +", gender: " +str.getGender());
			
	   }
	}
	
	public void markAttendance() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = new Date();
		String strDate = formatter.format(date);
        System.out.println("Mark Attendance for "+strDate);
        
		System.out.println("Please State 1 for Present and 2 for Absent ");
		//loop the Employee Array
		for(Employee str: arrayOfEmployee){
			System.out.print("employeeId: "+str.getEmployeeId()+", name: " + str.getName() +", gender: " +str.getGender()+ " : ");
			int isPresentInput = input.nextInt();
			boolean isPresent = (isPresentInput == 1);
			arrayOfMarkAttendance.add(new Attendance(strDate,str.getEmployeeId(),isPresent));
	   }
		
	   writeAttendance();
	}
		
	public void writeAttendance() {
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"attendance.txt"), true));
			for(Attendance str: arrayOfMarkAttendance){
				arrayOfAttendance.add(new Attendance(str.getDate(),str.getEmployeeId(),str.isPresent));
				String text =str.getDate()+":"+str.getEmployeeId()+"," + str.isPresent;
			      bw.write(text);
			      bw.newLine();
			}
			bw.newLine();
			bw.close();
		    System.out.println("Attedance Succesfully");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		   }
	}

	public void getAttendanceList() {
		System.out.println("Attendance List");
		System.out.println("\n------------------");
		System.out.println(" Attendance List");
		System.out.println("--------------------\n");
		System.out
				.println("----------------------------------------------------------------");
		System.out.println("Date \t\tEmployee ID\tis Present Status");
		System.out
				.println("----------------------------------------------------------------\n");
		for (Attendance str: arrayOfAttendance) {
			String currentDate = str.getDate();
			System.out.printf("%1$s\t%2$s\t\t%3$s\n",
					str.getDate(), str.getEmployeeId(), str.isPresent);
		}
//		for(Attendance str: arrayOfAttendance){
//			String text ="Date" +str.getDate()+": EmployeeId"+str.getEmployeeId()+"," + str.isPresent;
//		}
	}

	public void getAttendance(int employeeId) {
		System.out.println("An Attendance for "+employeeId);
		for(Attendance str: arrayOfAttendance){
			if(str.getEmployeeId() == employeeId) {
				String text =str.getDate()+":"+str.getEmployeeId()+"," + str.isPresent;
				System.out.println(text);
			}
		}
	}
	
	public void checkDate(String date) {
		
	}
}
