package designpatterns.adapterpattern.twosystems.thirdparty.billing;

public interface NewBillingSystem {

    void addPerson(Person person, Payment payment);

    Payment getBillingDetails(Person person);

}