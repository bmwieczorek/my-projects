package designpatterns.strategypattern;

import designpatterns.strategypattern.domain.Company;
import designpatterns.strategypattern.domain.Employee;
import designpatterns.strategypattern.domain.TaxPayer;
import designpatterns.strategypattern.stategy.CompanyTaxStrategy;
import designpatterns.strategypattern.stategy.EmployeeTaxStrategy;
import designpatterns.strategypattern.stategy.TaxStrategy;

public class RunExample {

	public static void main(String[] args) {
		TaxStrategy<Company> companyTaxStrategy = new CompanyTaxStrategy();
		TaxPayer<Company> smallCompany = new Company(companyTaxStrategy, 2.0, 10);
		System.out.println(smallCompany.calculate());
		
		//class cast exception 
		//TaxStrategy employeeTaxStrategy = new CompanyTaxStrategy();
		
		//compilation error
		//TaxStrategy<Employee> employeeTaxStrategy = new CompanyTaxStrategy();
		TaxStrategy<Employee> employeeTaxStrategy = new EmployeeTaxStrategy();
		TaxPayer<Employee> marriedEmployee = new Employee(employeeTaxStrategy, 2.0, false);
		System.out.println(marriedEmployee.calculate());

	}

}
