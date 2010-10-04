package designpatterns.adapterpattern.twosystems.homegrown.billing;

public interface BillingSystem {

    void addPerson(String pesel, String name, int salary);

    int getBillingDetails(String pesel, String employeeName);

}