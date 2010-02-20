package com.bawi.services.calculator;

public class ObjectFactoryEx extends ObjectFactory {

	@Override
	public CalculatorRQ createCalculatorRQ() {
		return new CalculatorRQEx();
	}
}
