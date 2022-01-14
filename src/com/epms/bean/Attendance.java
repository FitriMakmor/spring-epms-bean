package com.epms.bean;

import java.util.Date;

public class Attendance {

	int employeeId;
	boolean isPresent;
	String date;
	
	public Attendance() {
		
	}
	
	public Attendance(String date, int id, boolean isPresent) {
		this.date = date;
		this.employeeId = id;
		this.isPresent=isPresent;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public boolean isPresent() {
		return isPresent;
	}
	
	public String getDate() {
		return date;
	}
}
