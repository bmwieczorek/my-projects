package designpatterns.strategypattern.stategy;

import designpatterns.strategypattern.domain.Employee;

public class EmployeeTaxStrategy implements TaxStrategy<Employee> {

	private static double MARRIED_EMPLOYEE_TAX_RATE = 0.21;
	private static double SINGLE_EMPLOYEE_TAX_RATE = 0.31;

	public double calculate(Employee employee) {
		if (employee.isMarried()) {
			return MARRIED_EMPLOYEE_TAX_RATE * employee.getIncome(); 
		} else {
			return SINGLE_EMPLOYEE_TAX_RATE * employee.getIncome();
		}
	}
}
