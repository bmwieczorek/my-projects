package designpatterns.adapterpattern.twosystems;

import designpatterns.adapterpattern.twosystems.adapter.NewBillingSystemAdapter;
import designpatterns.adapterpattern.twosystems.homegrown.billing.BillingSystem;
import designpatterns.adapterpattern.twosystems.homegrown.billing.BillingSystemImpl;
import designpatterns.adapterpattern.twosystems.hr.Employee;
import designpatterns.adapterpattern.twosystems.hr.EmployeeDetails;
import designpatterns.adapterpattern.twosystems.hr.HRSystem;
import designpatterns.adapterpattern.twosystems.thirdparty.billing.NewBillingSystemImpl;

public class HRAndBillingSystemIntegration {

    public static void main(String[] args) {
        BillingSystem billingSystem = new BillingSystemImpl();
        BillingSystem adaptedBillingSystem = new NewBillingSystemAdapter(new NewBillingSystemImpl());

        String employeeName = "Will Smith";
        String employeePesel = "00112212345";
        int salary = 2000;
        billingSystem.addPerson(employeePesel, employeeName, salary);
        adaptedBillingSystem.addPerson(employeePesel, employeeName, salary);

        // HRSystem hrSystem = new HRSystem(billing);
        HRSystem hrSystem = new HRSystem(adaptedBillingSystem);

        hrSystem.addEmployee(new Employee(employeeName, employeePesel));
        EmployeeDetails employeeDetails = hrSystem.getEmployeeDetails(employeePesel, employeeName);
        System.out.println(employeeDetails.getSalary());

    }
}
