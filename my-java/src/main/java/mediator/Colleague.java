package mediator;

public interface Colleague {

    /**
     * Retrieve the name of the colleague.
     */
    public String getName();

    /**
     * Tell colleague to use a particular forum.
     */
    public void setForum(Forum forum);

    /**
     * Send a message to another colleague, via the forum.
     */
    public void send(String to, String message);

    /**
     * Send a message to all colleagues in the forum.
     */
    public void send(String message);

    /**
     * Receive a message from the forum.
     */
    public void receive(String from, String message);
}