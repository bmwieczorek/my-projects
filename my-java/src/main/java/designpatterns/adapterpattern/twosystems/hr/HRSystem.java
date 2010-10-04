package designpatterns.adapterpattern.twosystems.hr;

import java.util.HashMap;
import java.util.Map;

import designpatterns.adapterpattern.twosystems.homegrown.billing.BillingSystem;

public class HRSystem {
    private Map<String, Employee> idsToEmployees = new HashMap<String, Employee>();

    private final BillingSystem billingSystem;

    public HRSystem(BillingSystem billingSystem) {
        this.billingSystem = billingSystem;
    }

    public EmployeeDetails getEmployeeDetails(String pesel, String employeeName) {
        Employee employee = idsToEmployees.get(pesel);
        int salary = billingSystem.getBillingDetails(pesel, employeeName);
        return new EmployeeDetails(employee, salary);
    }

    public void addEmployee(Employee employee) {
        idsToEmployees.put(employee.getPesel(), employee);
    }
}