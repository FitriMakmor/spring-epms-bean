package com.epms.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class EmployeeManagement2 {
//	private int id;
//	private String name;
//	private String gender;
	private List<Employee2> emp;
	
	public EmployeeManagement2() {
		this.emp = new ArrayList<>();
		
		
	}
	
	public boolean addEmployee(int id, String name, String gender) {
		return emp.add(new Employee2(id, name, gender));
	}
	
	public List<Employee2> getEmployeeList(){
		return emp;
	}
	
	public boolean setEmployee(int id, String name, String gender) {
		ListIterator<Employee2> i = emp.listIterator();
		while(i.hasNext()) {
			Employee2 e = i.next();
			if(e.id == id) {
				i.set(new Employee2(id,name,gender));
				return true;
			}
			
		}
		return false;
	}
	
	public Employee2 getEmployee(int id) {
		for(Employee2 e : emp) {
			if(e.id == id) {
				return e;
			}
		}
		
		return null;
	}
	
	public boolean deleteEmployee(int id) {
		Iterator<Employee2> i = emp.iterator();
		while(i.hasNext()) {
			Employee2 e = i.next();
			if(e.id == id) {
				return emp.remove(e);
			}
			
		}
		return false;
	}
	
//	public String getName() {
//		return name;
//	}
//	
//	public String getGender() {
//		return gender;
//	}
//	
//	public void setId(int id) {
//		this.id = id;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
////	
////	public String toString() {
////		return id + " " + name + " " + gender;
////		
////	}
////	public void method() {
////		System.out.println("he");
////	}

}
