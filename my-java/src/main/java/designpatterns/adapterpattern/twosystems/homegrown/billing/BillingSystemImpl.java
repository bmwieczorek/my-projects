package designpatterns.adapterpattern.twosystems.homegrown.billing;

import java.util.HashMap;
import java.util.Map;

public class BillingSystemImpl implements BillingSystem {
    private Map<Person, Integer> personPayments = new HashMap<Person, Integer>();

    @Override
    public void addPerson(String pesel, String name, int salary) {
        personPayments.put(new Person(pesel, name), salary);
    }

    @Override
    public int getBillingDetails(String pesel, String employeeName) {
        return personPayments.get(new Person(pesel, employeeName));
    }
}