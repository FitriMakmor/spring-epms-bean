
package com.epms.bean;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PaymentTest {

    public static void main(String[] args) {
        PaymentManagement payment = new PaymentManagement();
        
        Employee employee1 = new Employee(1, "John", 'M');
        Employee employee2 = new Employee(2, "Fatimah", 'F');
        Employee employee3 = new Employee(3, "Joe", 'M');
        Employee employee4 = new Employee(4, "Eugene", 'M');
        
//        fsefsef
        
        payment.init();
//        payment.executePayment(employee4);
//        payment.executePayment(employee2);
//        payment.executePayment(employee3);
        payment.getEmployeeTransaction();
		
//        List<Transaction> transactions = payment.getEmployeeTransaction();
//
//        Iterator<Transaction> iterator = transactions.iterator();
//
//        while(iterator.hasNext()){
//                Transaction transaction = iterator.next();
//                System.out.println(transaction.getAmount());
//        }

        System.out.println("---------------------");
//        List<Transaction> transactions2 = payment.getEmployeeIndividualTransaction(new Employee(2, "Fatimah", 'F'));
//        Iterator<Transaction> iterator2 = transactions2.iterator();
//
//        while(iterator2.hasNext()){
//                Transaction transaction = iterator2.next();
//                System.out.println(transaction.getEmployeeID());
//        }
        
//        payment.setPayroll(employee1);
//        payment.setPayroll(employee2);
//        
//        System.out.println(payment.getEmployeePayroll(employee1));
//        payment.setPayroll(employee1);
//        
//        payment.deletePayroll(employee1);
//        System.out.println(payment.getEmployeePayroll(employee1));
//        System.out.println(payment.getEmployeePayroll(employee2));
        
        Scanner s = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("1 - Set Payroll, 2 - Get Payroll, 3 - Delete payroll, 4 - exit");
            choice = s.nextInt();
            switch(choice){
                case 1:
                    payment.setPayroll(employee1);
                    break;
                case 2:
                    System.out.println(payment.getEmployeePayroll(employee1));
                    break;
                case 3:
                    payment.deletePayroll(employee1);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Enter valid number");
            }
        }while(choice!=4);
        
        
    }
    
}
