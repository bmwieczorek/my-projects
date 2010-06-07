package designpatterns.strategypattern.domain;

import designpatterns.strategypattern.stategy.TaxStrategy;

public class Employee extends TaxPayer<Employee> {

    private boolean isMarried;

    public Employee(TaxStrategy<Employee> taxStrategy, double income, boolean isMarried) {
        super(taxStrategy, income);
        this.isMarried = isMarried;
    }

    public boolean isMarried() {
        return isMarried;
    }

    @Override
    protected Employee getTaxPayer() {
        return this;
    }

}
