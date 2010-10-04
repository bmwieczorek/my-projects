package designpatterns.adapterpattern.twosystems.adapter;

import designpatterns.adapterpattern.twosystems.homegrown.billing.BillingSystem;
import designpatterns.adapterpattern.twosystems.thirdparty.billing.NewBillingSystem;
import designpatterns.adapterpattern.twosystems.thirdparty.billing.Payment;
import designpatterns.adapterpattern.twosystems.thirdparty.billing.Person;

public class NewBillingSystemAdapter implements BillingSystem {

    private final NewBillingSystem newBillingSystem;
    private final PeselToBirthDateConverter converter = new PeselToBirthDateConverter();

    public NewBillingSystemAdapter(NewBillingSystem newBillingSystem) {
        this.newBillingSystem = newBillingSystem;
    }

    @Override
    public void addPerson(String pesel, String employeeName, int salary) {
        newBillingSystem.addPerson(new Person(employeeName, converter.convert(pesel)), new Payment(salary));

    }

    @Override
    public int getBillingDetails(String pesel, String employeeName) {
        Person person = new Person(employeeName, converter.convert(pesel));
        Payment billingDetails = newBillingSystem.getBillingDetails(person);
        return billingDetails.getSalary();
    }

}
