package com.epms.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PaymentManagement {
    Payroll payroll;
    Employee employee;
    List<Transaction> transactions = new ArrayList<Transaction>();
    Map<Integer, Payroll> payrolls = new HashMap<>();
    
    
    FileInputStream transactionFile = null;
    
    
    public void init() {
		try {
				transactionFile = new FileInputStream("transaction.txt");     
				Scanner scan = new Scanner(transactionFile); 
				while(scan.hasNextLine())  {
					String line = scan.nextLine();
					String[] words = line.split(",");
					
					String stringDate = words[2];  
				    Date date;
					try {
						date = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(stringDate);
						transactions.add(new Transaction(Integer.parseInt(words[0]), Double.parseDouble(words[1]), date));
					} catch (ParseException e) {
						e.printStackTrace();
					}  
					
				} 
				scan.close();
				System.out.println("Transaction Data Read Successfully");
		}  
		catch(IOException e)  {  
		e.printStackTrace();  
		}  
	}
    
    public List<Transaction> getEmployeeTransaction() {
    	
    	Iterator<Transaction> iterator = transactions.iterator();
    	
    	System.out.println("List of transaction");
        while(iterator.hasNext()){
            Transaction transaction = iterator.next();
            String text = 
            		"Employee ID: " + transaction.getEmployeeID() +
            		", Transaction Amount: " + transaction.getAmount() + 
            		", Transaction Date: " + transaction.getDate().toString();
            System.out.println(text);
        }
        
        return transactions;
    }
    
    public Payroll getEmployeePayroll(Employee employee){
        if(payrolls.containsKey(employee.getEmployeeId())){
            System.out.println("Payroll for "+ employee.getName()+" found");
            return payrolls.get(employee.getEmployeeId());
        }
        else{
            System.out.println("Payroll for " + employee.getName()+" not found");
            return null;
        }
    }
    
    public void setPayroll(Employee employee){
        Scanner s = new Scanner(System.in);
        if(payrolls.containsKey(employee.getEmployeeId())){
            System.out.println("Current payroll is");
            payrolls.get(employee.getEmployeeId()).toString();
            
            System.out.println("Enter updated basic salary: ");
            double basic = s.nextDouble();

            System.out.println("Enter overtime amount: ");
            double ot = s.nextDouble();

            System.out.println("Enter bonus amount: ");
            double bonus = s.nextDouble();

            System.out.println("Enter others amount: ");
            double others = s.nextDouble();

            payroll = new Payroll(employee.getEmployeeId(), basic, ot, bonus, others);
            payrolls.put(employee.getEmployeeId(), payroll);
            
        }
        else{
            System.out.println("Payroll for employee does not exist");
            System.out.println("Set payroll for employee? (Y/N)");
            String choice = s.nextLine();
            if("y".equals(choice)){
                System.out.println("Enter basic salary: ");
                double basic = s.nextDouble();

                System.out.println("Enter overtime amount: ");
                double ot = s.nextDouble();

                System.out.println("Enter bonus amount: ");
                double bonus = s.nextDouble();

                System.out.println("Enter others amount: ");
                double others = s.nextDouble();
                payroll = new Payroll(employee.getEmployeeId(), basic, ot, bonus, others);
                payrolls.put(employee.getEmployeeId(), payroll);
                System.out.println(payroll.toString());
            }else{
                System.out.println("Understood have a nice day");
            }
        }
    }
    
    public void deletePayroll(Employee employee){
        if(!payrolls.containsKey(employee.getEmployeeId())){
            return;
        }
        else{
            payrolls.remove(employee.getEmployeeId());
        }
    }
    
    public void executePayment(Employee employee) {
    	
    	System.out.println("Enter employee ID: ");
//    	int employeeID = Scan. "";
        Payroll paymentPayroll = getEmployeePayroll(employee);
        if(paymentPayroll == null){
            setPayroll(employee);
            paymentPayroll = getEmployeePayroll(employee);
        }
        double amount = paymentPayroll.basicSalary + paymentPayroll.bonus + paymentPayroll.overtime + paymentPayroll.others;
        
        try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("transaction.txt"), true));
			Date date = new Date();
			Transaction transaction = new Transaction(employee.getEmployeeId(), amount, date);
	        transactions.add(transaction);
	        String text = employee.getEmployeeId() + "," + amount + "," + date.toString();
	        bw.write(text);
	        bw.newLine();
	        bw.close();

	    	System.out.println("Payment Executed Succesfully");
	    	
	    }catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	   }
    }
    
    
    public List<Transaction> getEmployeeIndividualTransaction(int employeeId) {
        List<Transaction> individualTransactions = new ArrayList<Transaction>();

        Iterator<Transaction> iterator = transactions.iterator();

        while(iterator.hasNext()){
            Transaction transaction = iterator.next();

			if(transaction.getEmployeeID() == employeeId) {
                    individualTransactions.add(transaction);
            }
        }

        return individualTransactions;
    }
}
