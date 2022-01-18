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
    FileInputStream payrollFile = null;
    
    
    public void loadTransactionData() {
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
			
			
			System.out.println("Transaction Data Loaded");
		}  
		catch(IOException e)  {  
			e.printStackTrace();  
		}  
	}
    
    public void loadPayrollData() {
    	
    	try {
    		payrollFile = new FileInputStream("payroll.txt");
    		
			Scanner scan = new Scanner(payrollFile);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				String[] words = line.split(",");
				Payroll temp = new Payroll(Integer.parseInt(words[0]), Double.parseDouble(words[1]), Double.parseDouble(words[2]), Double.parseDouble(words[3]), Double.parseDouble(words[4]));
				payrolls.put(Integer.parseInt(words[0]), temp);
			}
			
			scan.close();
			
			System.out.println("Payroll Data Loaded");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    }
    
    public List<Transaction> getEmployeeTransaction() {
    	System.out.println();
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
    
    public List<Transaction> getEmployeeIndividualTransaction() {
    	System.out.println();
    	Scanner scan = new Scanner(System.in);
    	boolean exist = false;
    	
    	System.out.println("Enter Employee ID: ");
    	int employeeId = scan.nextInt();
        
    	
    	List<Transaction> individualTransactions = new ArrayList<Transaction>();
        
        Iterator<Transaction> iterator = transactions.iterator();

        while(iterator.hasNext()){
            Transaction transaction = iterator.next();

			if(transaction.getEmployeeID() == employeeId) {
                    individualTransactions.add(transaction);
                    exist = true;
                    String text = 
                    		"Employee ID: " + transaction.getEmployeeID() +
                    		", Transaction Amount: " + transaction.getAmount() + 
                    		", Transaction Date: " + transaction.getDate().toString();
                    System.out.println(text);
            }
        }
        
        if(!exist) {
        	System.out.println("Transaction Data for Employee ID [" + employeeId + "] does not exists.");
        }
        
        return individualTransactions;
    }
    
    public Payroll getEmployeePayroll(int employeeId){
    	System.out.println();
    	
        if(payrolls.containsKey(employeeId)){
            System.out.println("Payroll for employee ID" + employeeId +" found");
            return payrolls.get(employeeId);
        }else{
            System.out.println("Payroll for employee ID " + employeeId + " not found");
            return null;
        }
    }
    
    public void getEmployeePayrolls(){
//        for( int key: payrolls.keySet() ){
//            System.out.print(key+" ");
//            System.out.println(payrolls.get(key));
//            Payroll temp = payrolls.get(key);
//        }
        
        if(payrolls.isEmpty()) {
			System.out.println("No Payrolls Available");
		}
		else {
			System.out.println("\n------------------");
			System.out.println(" Employee List");
			System.out.println("--------------------\n");
			System.out
					.println("----------------------------------------------------------------------");
			System.out.println("Employee ID\t|Basic Salary\t|Overtime\t|Bonus\t\t|Others");
			System.out
					.println("----------------------------------------------------------------------\n");
			for(int key: payrolls.keySet()){
				Payroll payroll = payrolls.get(key);				
				System.out.printf("%d\t\t|RM%.2f\t|RM%.2f\t|RM%.2f\t|RM%.2f\n",
						payroll.getEmployeeID(), payroll.getBasicSalary(), payroll.getOvertime(), 
						payroll.getBonus(), payroll.getOthers());
			}	
	   }
}
    
    public void setPayroll(int employeeId){
    	System.out.println();
        Scanner s = new Scanner(System.in);
        
        if(payrolls.containsKey(employeeId)){
            System.out.println("Current payroll is " + payrolls.get(employeeId).toString());
            
            System.out.println("Enter updated basic salary: ");
            double basic = s.nextDouble();

            System.out.println("Enter overtime amount: ");
            double ot = s.nextDouble();

            System.out.println("Enter bonus amount: ");
            double bonus = s.nextDouble();

            System.out.println("Enter others amount: ");
            double others = s.nextDouble();

            payroll = new Payroll(employeeId, basic, ot, bonus, others);
            payrolls.put(employeeId, payroll);
            updatePayrolls();
            
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
                payroll = new Payroll(employeeId, basic, ot, bonus, others);
                payrolls.put(employeeId, payroll);
//                System.out.println(payroll.toString());
                updatePayrolls();
            }else{
                System.out.println("Understood have a nice day");
            }
        }
    }
    
    public void deletePayroll(){
    	
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Enter Employee ID: ");
    	int employeeId = scan.nextInt();
    	
        if(!payrolls.containsKey(employeeId)){
            return;
        }
        else{
            payrolls.remove(employeeId);
            updatePayrolls();
        }
    }
    
    public void updatePayrolls(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("payroll.txt"), false));
            for( int key: payrolls.keySet() ){
//                System.out.print(key+" ");
//                System.out.println(payrolls.get(key));
                Payroll temp = payrolls.get(key);
                String update = temp.getEmployeeID()+","+temp.getBasicSalary()+","+temp.getOvertime()+","+temp.getBonus()+","+temp.getBonus();
                bw.write(update);
                bw.newLine();
            }
            bw.close();
            System.out.println("\nPayrolls updated successfully!");
        }catch(IOException e){
            System.out.println("\nAn error occured while updating the payrolls");
            e.printStackTrace();
        }
    }
    
    public void executePayment(int employeeId) {
//    	System.out.println();
//    	System.out.println("Enter employee ID: ");
//    	
        Payroll paymentPayroll = getEmployeePayroll(employeeId);
        if(paymentPayroll == null){
            setPayroll(employeeId);
            paymentPayroll = getEmployeePayroll(employeeId);
        }
        double amount = paymentPayroll.basicSalary + paymentPayroll.bonus + paymentPayroll.overtime + paymentPayroll.others;
        
        try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("transaction.txt"), true));
			Date date = new Date();
			Transaction transaction = new Transaction(employeeId, amount, date);
	        transactions.add(transaction);
	        String text = employeeId + "," + amount + "," + date.toString();
	        bw.write(text);
	        bw.newLine();
	        bw.close();

	    	System.out.println("Payment Executed Succesfully");
	    	
	    }catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	   }
    }
}
