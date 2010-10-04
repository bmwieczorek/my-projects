package designpatterns.adapterpattern.twosystems.thirdparty.billing;

public class Payment {
    private int salary;

    public Payment(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}