package designpatterns.strategypattern.domain;

import designpatterns.strategypattern.stategy.TaxStrategy;

public class Company extends TaxPayer<Company> {
    int noEmployees;

    public Company(TaxStrategy<Company> taxStrategy, double income, int noEmployees) {
        super(taxStrategy, income);
        this.noEmployees = noEmployees;
    }

    public int getNoEmployees() {
        return noEmployees;
    }

    @Override
    protected Company getTaxPayer() {
        return this;
    }
}
