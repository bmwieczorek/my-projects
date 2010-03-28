package callback;

import java.util.Collection;

public class DataStoreExample extends DataStoreTemplate {
    public static void main(String[] args) {
        DataStoreExample dataStoreAccess = new DataStoreExample();
        Person person =
                dataStoreAccess.queryForObject(new CallbackMapper<Person>() {

                    public Person map(MySet<Person> src) {
                        Person person = new Person();
                        person.name = src.getString("name");
                        person.age = src.getInt("age");
                        return person;
                    }
                });
        System.out.println(person);

        Collection<Person> people =
                dataStoreAccess.query(new CallbackMapper<Person>() {

                    public Person map(MySet<Person> src) {
                        Person person = new Person();
                        person.name = src.getString("name");
                        person.age = src.getInt("age");
                        return person;
                    }
                });
        System.out.println(people);
    }
}
