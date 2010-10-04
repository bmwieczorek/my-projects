package designpatterns.adapterpattern.twosystems.hr;

public class EmployeeDetails extends Employee {
    private final int salary;

    public EmployeeDetails(Employee employee, int salary) {
        super(employee.getName(), employee.getPesel());
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

}