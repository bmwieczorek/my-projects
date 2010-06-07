package designpatterns.strategypattern.stategy;

import designpatterns.strategypattern.domain.TaxPayer;

public interface TaxStrategy<P extends TaxPayer<P>> {

    double calculate(P p);

}
