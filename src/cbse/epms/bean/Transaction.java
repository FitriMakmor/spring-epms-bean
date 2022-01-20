package cbse.epms.bean;

import java.util.Date;

public class Transaction {
	
	private int employeeID;
	private double amount;
	private Date date;
	
	public Transaction(int employeeID, double amount, Date date) {
            this.employeeID = employeeID;
            this.amount = amount;
            this.date = date;
	}

	
	// auto generated getter and setter
	public int getEmployeeID() {
            return employeeID;
	}

	public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
	}

	public double getAmount() {
            return amount;
	}

	public void setAmount(double amount) {
            this.amount = amount;
	}

	public Date getDate() {
            return date;
	}

	public void setDate(Date date) {
            this.date = date;
	}
	
	
}
