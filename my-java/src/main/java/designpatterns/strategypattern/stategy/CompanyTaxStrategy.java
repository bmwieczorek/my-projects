package designpatterns.strategypattern.stategy;

import designpatterns.strategypattern.domain.Company;

public class CompanyTaxStrategy implements TaxStrategy<Company> {

    private static double SMALL_COMPANY_TAX_RATE = 0.20;
    private static double BIG_COMPANY_TAX_RATE = 0.30;

    public double calculate(Company company) {
        if (company.getNoEmployees() < 100) {
            return SMALL_COMPANY_TAX_RATE * company.getIncome();
        } else {
            return BIG_COMPANY_TAX_RATE * company.getIncome();
        }
    }

}
