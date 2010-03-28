package designpatterns.strategypattern.domain;

import designpatterns.strategypattern.stategy.TaxStrategy;

public abstract class TaxPayer<P extends TaxPayer<P>> {
	private TaxStrategy<P> taxStrategy;
	private double income;

	public TaxPayer(TaxStrategy<P> taxStrategy, double income) {
		this.taxStrategy = taxStrategy;
		this.income = income;
	}

	protected abstract P getTaxPayer();

	public double calculate() {
		return taxStrategy.calculate(getTaxPayer());
	}

	public double getIncome() {
		return income;
	}

}
