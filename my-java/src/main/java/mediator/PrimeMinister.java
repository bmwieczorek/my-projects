package mediator;

public class PrimeMinister extends MemberOfParliament {

    public PrimeMinister(String name) {
        super(name);
    }

    @Override
    public void receive(String from, String message) {
        System.out.println("Prime Minister '" + getName() + "' received a message from '" + from + "': "
                + message);
    }
}