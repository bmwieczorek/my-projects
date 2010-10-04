package designpatterns.adapterpattern.twosystems.hr;

public class Employee {
    private final String name;
    private final String pesel;

    public Employee(String name, String pesel) {
        this.name = name;
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

}
