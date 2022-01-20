package cbse.epms.bean;

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

    public int getEmployeeID() {
        return employeeID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getOvertime() {
        return overtime;
    }

    public double getBonus() {
        return bonus;
    }

    public double getOthers() {
        return others;
    }

    @Override
    public String toString() {
        return "Payroll{" + "employeeID = " + employeeID + ", basicSalary = " + basicSalary + ", overtime = " + overtime + ", bonus = " + bonus + ", others = " + others + '}';
//        return "Payroll{" + "Employee = " + employee + ", basicSalary = " + basicSalary + ", overtime = " + overtime + ", bonus = " + bonus + ", others = " + others + '}';
    }
    
}
