package com.epms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Rezamir
 */
public class PaymentManagement {
    Payroll payroll;
    Employee employee;
    List<Transaction> transactions = new ArrayList<Transaction>();
    Map<Integer, Payroll> payrolls = new HashMap<>();
    
    public List<Transaction> getEmployeeTransaction() {
        return transactions;
    }
    
    public Payroll getEmployeePayroll(Employee employee){
        if(payrolls.containsKey(employee.getEmployeeId())){
            System.out.println("Payroll for "+employee.getName()+" found");
            return payrolls.get(employee.getEmployeeId());
        }
        else{
            System.out.println("Payroll for " +employee.getName()+" not found");
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
        // get payroll by employee
        // get total payroll for that employee
        // add transaction by employee id
        Payroll paymentPayroll = getEmployeePayroll(employee);
        if(paymentPayroll == null){
            setPayroll(employee);
            paymentPayroll = getEmployeePayroll(employee);
        }
        double amount = paymentPayroll.basicSalary + paymentPayroll.bonus + paymentPayroll.overtime + paymentPayroll.others;
        Transaction transaction = new Transaction(employee.getEmployeeId(), amount, new Date());
        transactions.add(transaction);

        //double amount = get dari payroll
        //Transaction transaction = new Transaction(employee.getEmployeeId(), amount, new Date());

        //----- dummyy, suposely one transaction per execution -----------
        //Transaction transaction = new Transaction(0, 0, new Date());
        //Transaction transaction2 = new Transaction(3, 0, new Date());
        //Transaction transaction3 = new Transaction(1, 0, new Date());

        //transactions.add(transaction);
        //transactions.add(transaction);
        //transactions.add(transaction2);
        //transactions.add(transaction3);
        //transactions.add(transaction3);
        //transactions.add(transaction3);
        //transactions.add(transaction2);
        //transactions.add(transaction2);
        //transactions.add(transaction);
        //transactions.add(transaction3);

        //-- end dummy --
    }
    
   
    
    public List<Transaction> getEmployeeIndividualTransaction(Employee employee) {
        List<Transaction> individualTransactions = new ArrayList<Transaction>();

        Iterator<Transaction> iterator = transactions.iterator();

        while(iterator.hasNext()){
            Transaction transaction = iterator.next();

    //			if(transaction.getEmployeeID() == employee.getId()) {
            if(transaction.getEmployeeID() == 1) { // assume id  = 1 lol
                    individualTransactions.add(transaction);
            }
        }

        return individualTransactions;
    }
}
