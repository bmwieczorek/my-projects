package designpatterns.adapterpattern.twosystems.homegrown.billing;

public final class Person {
    private final String pesel;
    private final String name;

    public Person(String pesel, String name) {
        this.pesel = pesel;
        this.name = name;
    }

    public String getPesel() {
        return pesel;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pesel == null) {
            if (other.pesel != null)
                return false;
        } else if (!pesel.equals(other.pesel))
            return false;
        return true;
    }

}
