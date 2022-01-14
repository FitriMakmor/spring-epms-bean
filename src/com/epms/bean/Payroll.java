package com.epms.bean;

/**
 *
 * @author Rezamir
 */
public class Payroll {
    int employeeID;
    Employee employee;
    double basicSalary, overtime, bonus, others;
    
    public Payroll(int employeeID, double basicSalary, double overtime, double bonus, double others){
//    public Payroll(Employee employee, double basicSalary, double overtime, double bonus, double others){
        this.employeeID = employeeID;
//        this.employee = employee;
        this.basicSalary = basicSalary;
        this.overtime = overtime;
        this.bonus = bonus;
        this.others = others;
    }

    @Override
    public String toString() {
        return "Payroll{" + "employeeID = " + employeeID + ", basicSalary = " + basicSalary + ", overtime = " + overtime + ", bonus = " + bonus + ", others = " + others + '}';
//        return "Payroll{" + "Employee = " + employee + ", basicSalary = " + basicSalary + ", overtime = " + overtime + ", bonus = " + bonus + ", others = " + others + '}';
    }
    
}
