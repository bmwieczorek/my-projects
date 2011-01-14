package mediator;

public interface Forum {

    /**
     * Add a new colleague to the forum, allowing them to send and receive
     * messages with other colleagues.
     */
    public void add(Colleague c);

    /**
     * Send a message to all colleagues in the forum.
     */
    public void send(String from, String message);

    /**
     * Send a message to a specific colleague in the forum.
     */
    public void send(String from, String to, String message);

}
