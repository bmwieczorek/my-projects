package designpatterns.adapterpattern.twosystems.thirdparty.billing;

import java.util.Date;

public class Person {

    private String name;
    private Date birthDate;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthDate == null) ? 0 : birthDate.getYear());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.getMonth());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.getDate());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (birthDate.getYear() != other.birthDate.getYear()
                || birthDate.getMonth() != other.birthDate.getMonth()
                || birthDate.getDate() != other.birthDate.getDate())
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
