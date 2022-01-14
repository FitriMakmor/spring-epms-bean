package com.epms.bean;

public class Employee2 {
	int id;
	String name;
	String gender;
	
	public Employee2(int id, String name, String gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
	
	public String toString() {
		
		return "EMPLOYEE ID: " + id + " EMPLOYEE NAME: " + name + " EMPLOYEE GENDER: " + gender;
	}
	
	
	
}
