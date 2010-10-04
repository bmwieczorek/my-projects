package designpatterns.adapterpattern.twosystems.homegrown.billing;

class Payment {
    private int salary;

    public Payment(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}