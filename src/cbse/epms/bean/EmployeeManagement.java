package cbse.epms.bean;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

public class EmployeeManagement{
	private static Scanner x;
	   
    ArrayList<Attendance> arrayOfAttendance= new ArrayList<>();
    ArrayList<Attendance> arrayOfMarkAttendance = new ArrayList<>();
    ArrayList<Employee> arrayOfEmployee= new ArrayList<>();
    
	FileInputStream employeeFile = null;
	FileInputStream attendanceFile = null;
	
	Scanner input = new Scanner(System.in);
	Scanner input2= new Scanner(System.in);
	
	//read Data of Employee
	public void initReadEmployeeFile() {
		try {
			    employeeFile = new FileInputStream("employee.txt");     
				Scanner sc=new Scanner(employeeFile); 
				while(sc.hasNextLine())  {
					String line = sc.nextLine();
					String[] words=line.split(",");
					arrayOfEmployee.add(new Employee(Integer.parseInt(words[0]),words[1],words[2].charAt(0)));
				} 
				
				sc.close();     //closes the scanner  
				
				System.out.println("Employee Data Loaded");
		}  
		catch(IOException e)  {  
		e.printStackTrace();  
		}  
	}
	
	//read Data of Attendance
	public void initReadAttendanceFile() {
		try {
		    attendanceFile = new FileInputStream("attendance.txt");     
			Scanner sc=new Scanner(attendanceFile); 
			while(sc.hasNextLine())  {
				String line = sc.nextLine();
				if ( line.trim().length() == 0 ) {
				    continue;  // Skip blank lines
				}
				String[] words=line.split(",");
				boolean isPresent=Boolean.parseBoolean(words[2]);
				arrayOfAttendance.add(new Attendance(words[0],Integer.parseInt(words[1]),isPresent));
			
			}
			sc.close();     //closes the scanner  
			
			System.out.println("Attendance Data Loaded");
		}  
		catch(IOException e)  {  
			e.printStackTrace();  
		} 
	}
		
	//add New Employee
	public boolean addEmployee() {
		System.out.print("Enter id: ");
		int id = input.nextInt();
		System.out.print("Enter name: ");
		String name = input2.nextLine();
		System.out.print("Enter gender(M/F): ");
		String gender = input2.nextLine();
		
		char g = gender.charAt(0);
		writeEmployee(id,name,g);
		return arrayOfEmployee.add(new Employee(id, name, g));
	}
	
	//add New Employee at "employee.txt"
	public void writeEmployee(int id, String name, char gender) {
			try {
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
						"employee.txt"), true));
				String text =id+","+name+"," +gender;
				bw.write(text);
				bw.newLine();
				bw.close();
			    System.out.println("Succesfully Added Employee into The Employee Record");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			   }
	}
		
	//Update Employee
	public void setEmployee() {
		String name; String gender;
		System.out.print("Enter Employee  Id to update: ");
		int id = input.nextInt();
		System.out.println("----------------------------");
		
		if(getEmployee(id) != null) {
			System.out.print("Enter name: ");
			name = input2.nextLine();
			System.out.print("Enter gender(M/F): ");
			gender = input2.nextLine();
			
			ListIterator<Employee> i = arrayOfEmployee.listIterator();
			while(i.hasNext()) {
				Employee e = i.next();
				if(e.getEmployeeId() == id) {
					char g = gender.charAt(0);
					i.set(new Employee(id, name, g));
					editRecordEmployee("employee.txt",id,name,g);
					System.out.println("Record is updated successfully");
				}
				
			}
		}else {
			System.out.println("Record not found");	
		}
	}
	
	//update employee Data in "employee.txt"
	public void editRecordEmployee(String filepath, int IDtoEdit, String newName, char newGender){
		String tempFile = "temp.txt";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		
		
		int ID;
		String name = "";
		char gender;
		
		try {
			
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			PrintWriter pw = new PrintWriter(bw);
			x = new Scanner (new File(filepath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext()) {
				ID = Integer.parseInt(x.next());
				name = x.next();
				gender = x.next().charAt(0);
				
				if(ID == IDtoEdit) {
					pw.print(ID+ ","+newName+ ","+newGender+"\n");
				}else {
					pw.print(ID+ ","+name+ ","+gender+"\n");
				}
				
			}
			
			x.close();
			pw.flush();
			pw.close();
			
			
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
		
		    System.out.println("Edit Successful.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		   }
	}
	
	// inner method for getting Employee Details
	private Employee getEmployee(int id) {
		for(Employee e : arrayOfEmployee ) {
			if(e.getEmployeeId() == id) {
				return e;
			}
		}
		
		return null;
	}
	
	// displays the employee details on the console when record is found
	public void displayEmployeeDetails() {
		System.out.print("Enter the Employee ID : ");
		int id = input.nextInt();
		
		String text="";
		for(Employee e : arrayOfEmployee ) {
			if(e.getEmployeeId() == id) {
				text+= "\n-----------------";
				text+= "\nEmployee ID: " + e.getEmployeeId()+"\nName: "+e.getName()+"\nGender: ";
				text+= e.getGender()=='M' ? "Male" : "Female";
				text+= "\n-----------------";
				System.out.println(text);
			}
		}
		
		if(text.equals("")) {
			System.out.println("No employee record found for id: "+id);
		}
		
	}
	
	//deleteEmployee
	public void deleteEmployee() {
		System.out.print("Enter the Employee ID : ");
		int id = input.nextInt();
		boolean found = false;
		Iterator<Employee> i = arrayOfEmployee.iterator();
		while(i.hasNext()) {
			Employee e = i.next();
			if(e.getEmployeeId() == id) {
				deleteRecordEmployee(id);
				arrayOfEmployee.remove(e);
				System.out.println("Successfully removed "+id+" from the Employee Data");
				found =true;
				break;
			}
			
		}
		if(!found) {
			System.out.println("Unable to remove "+id+" as it does not exist in the Employee Data");	
		}
	
	}
	
	//delete data Employee from "employee.txt"
	public void deleteRecordEmployee(int employeeId) {
		try {
			File inputFile = new File("employee.txt");
			File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
         
			BufferedReader br = new BufferedReader(new FileReader("employee.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;
            
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
            	if ( line.trim().length() == 0 ) {
				    continue;  // Skip blank lines
				}
            	String[] words = line.trim().split(",");
                if (Integer.parseInt(words[0]) != employeeId) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();
        	inputFile.delete();
			File dump = new File("employee.txt");
			tempFile.renameTo(dump);
		
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	//display the list of Employee
	public void displayListOfEmployee(){
		if(arrayOfEmployee.isEmpty()) {
			System.out.println("No Employee List");
		}
		else {
			System.out.println("\n------------------");
			System.out.println(" Employee List");
			System.out.println("--------------------\n");
			System.out
					.println("-----------------------------------------------");
			System.out.println("Employee ID\t Name\t\t Gender");
			System.out
					.println("-----------------------------------------------\n");
			for(Employee str: arrayOfEmployee){
				System.out.printf("%1$s\t\t%2$s\t\t %3$s\n",
						str.getEmployeeId(), str.getName(), str.getGender());
			}	
	   }
	}
	
	// mark Attendance for All
	public String markAttendance() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = new Date();
		String strDate = formatter.format(date);
        System.out.println("Mark Attendance for "+strDate);
        
        if(checkExistDate(strDate)==true) {
        	System.out.println("You have already marked the attendance for today. Would you like to re-mark the Attendance?\n"
        			+ "Choose: 1. Re-mark, 2. Decline :");
        	int remark = input.nextInt();
        	if(remark == 1) {
        		deleteExistingRecordAttendance(strDate);
        	}
        	
        	else {
        		return "Re-marking of attendance declined.";
        	}
        }

		System.out.println("Please State 1. Present, 2. Absent for Each Employee");
		//loop the Employee Array
		for(Employee str: arrayOfEmployee){
			System.out.print("employeeId: "+str.getEmployeeId()+", name: " + str.getName() +", gender: " +str.getGender()+ " : ");
			int isPresentInput = input.nextInt();
			boolean isPresent = (isPresentInput == 1);
			arrayOfMarkAttendance.add(new Attendance(strDate,str.getEmployeeId(),isPresent));
	   }
		
	   writeAttendance();
	   return "Succesfully Marked The Attendance !";
	}
	
	public void deleteExistingRecordAttendance(String date) {
		arrayOfAttendance.clear();
		try {
			File inputFile = new File("attendance.txt");
			File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
         
			BufferedReader br = new BufferedReader(new FileReader("attendance.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;
            
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
            	if ( line.trim().length() == 0 ) {
				    continue;  // Skip blank lines
				}
            	String[] words = line.trim().split(",");
                if (!words[0].equals(date)) {
                    pw.println(line);
                    pw.flush();
                    boolean isPresent=Boolean.parseBoolean(words[2]);
    				arrayOfAttendance.add(new Attendance(words[0],Integer.parseInt(words[1]),isPresent));
                }
            }
            pw.close();
            br.close();
        	inputFile.delete();
			File dump = new File("attendance.txt");
			tempFile.renameTo(dump);
		
		}catch(IOException e) {
			e.printStackTrace();
		}
			
	
		
	}
	
    //Check in "attendance.txt" the date has been mark the attendance or not
	public boolean checkExistDate(String date) {
		for(Attendance str: arrayOfAttendance) {
			if(str.getDate().equalsIgnoreCase(date)) {
				return true;
			}
		}
		return false;
	}
	
	// add the attendance list in "attendance.txt"	
	public void writeAttendance() {
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"attendance.txt"), true));
			for(Attendance str: arrayOfMarkAttendance){
				arrayOfAttendance.add(new Attendance(str.getDate(),str.getEmployeeId(),str.isPresent));
				String text =str.getDate()+","+str.getEmployeeId()+"," + str.isPresent;
			      bw.write(text);
			      bw.newLine();
			}
			bw.newLine();
			bw.close();
		    System.out.println("Attendance Successfully Marked.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		   }
	}

	//get all attendance list
	public void getAttendanceList() {
		if(arrayOfAttendance.isEmpty()) {
			System.out.println("No Attendance List");
		}
		else {
			System.out.println("\n------------------");
			System.out.println(" Attendance List");
			System.out.println("--------------------\n");
			System.out
					.println("----------------------------------------------------------------");
			System.out.println("Date \t\tEmployee ID\tIs Present Status");
			System.out
					.println("----------------------------------------------------------------\n");
			for (Attendance str: arrayOfAttendance) {
				String currentDate = str.getDate();
				System.out.printf("%1$s\t%2$s\t\t%3$s\n",
						str.getDate(), str.getEmployeeId(), str.isPresent);
			}
		}
		
	}

	//get list of attendance for the specific employee
	public void getAttendance() {
		System.out.print("Enter Employee ID : ");
    	int employeeId = input.nextInt();
		String text="";
		System.out.println("Attendance Record for "+employeeId);
		for(Attendance str: arrayOfAttendance){
			if(str.getEmployeeId() == employeeId) {
				text =str.getDate()+":"+str.getEmployeeId()+"," + str.isPresent;
				System.out.println(text);
			}
		}
		
		if(text.equals("")) {
			System.out.println("Attendance Record for Employee ID "+employeeId+" not found");
		}
	}
	
}
