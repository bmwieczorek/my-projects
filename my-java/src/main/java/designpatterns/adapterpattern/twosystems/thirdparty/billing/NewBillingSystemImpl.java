package designpatterns.adapterpattern.twosystems.thirdparty.billing;

import java.util.HashMap;
import java.util.Map;

public class NewBillingSystemImpl implements NewBillingSystem {
    Map<Person, Payment> personPayments = new HashMap<Person, Payment>();

    @Override
    public void addPerson(Person person, Payment payment) {
        personPayments.put(person, payment);
    }

    @Override
    public Payment getBillingDetails(Person person) {
        return personPayments.get(person);
    }
}