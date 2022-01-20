package cbse.epms.bean;

public class Employee {

	int employeeId;
	String name;
	char gender;
	
	public Employee() {
		
	}
	
	public Employee(int id, String name, char gender) {
		this.employeeId = id;
		this.name = name;
		this.gender = gender;
		
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public String getName() {
		return name;
	}
	
	public char getGender() {
		return gender;
	}
	
	
	
}
