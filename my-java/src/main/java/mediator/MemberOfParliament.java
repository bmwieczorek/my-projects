package mediator;

public class MemberOfParliament implements Colleague {

    private String name;
    private Forum forum;

    public MemberOfParliament(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public void send(String to, String message) {
        System.out.println("'" + name + "' is sending a message to '" + to + "': " + message);
        forum.send(name, to, message);
    }

    public void send(String message) {
        System.out.println("'" + name + "' is sending a public message: " + message);
        forum.send(name, message);
    }

    public void receive(String from, String message) {
        System.out.println("The Hon. MP '" + name + "' received a message from '" + from + "': " + message);
    }

}