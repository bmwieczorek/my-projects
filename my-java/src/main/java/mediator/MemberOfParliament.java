package mediator;

public class MemberOfParliament implements Colleague {

    private String name;
    private Forum forum;

    public MemberOfParliament(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setForum(Forum forum) {
        this.forum = forum;
    }

    @Override
    public void send(String to, String message) {
        System.out.println("'" + name + "' is sending a message to '" + to + "': " + message);
        forum.send(name, to, message);
    }

    @Override
    public void send(String message) {
        System.out.println("'" + name + "' is sending a public message: " + message);
        forum.send(name, message);
    }

    @Override
    public void receive(String from, String message) {
        System.out.println("The Hon. MP '" + name + "' received a message from '" + from + "': " + message);
    }

}