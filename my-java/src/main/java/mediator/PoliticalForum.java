package mediator;

import java.util.HashMap;
import java.util.Iterator;

public class PoliticalForum implements Forum {
    private HashMap<String, Colleague> colleagues = new HashMap<String, Colleague>();

    @Override
    public void add(Colleague c) {
        this.colleagues.put(c.getName(), c);
        c.setForum(this);

        System.out.println("Added a colleague to the political forum: " + c.getName());
    }

    @Override
    public void send(String from, String message) {
        Colleague c;

        for (Iterator<Colleague> i = colleagues.values().iterator(); i.hasNext();) {
            c = i.next();
            if (!from.equals(c.getName()))
                c.receive(from, message);
        }
        System.out.println();
    }

    @Override
    public void send(String from, String to, String message) {
        Colleague c = colleagues.get(to);

        if (c != null)
            c.receive(from, message);
        else
            System.out.println(to + " is not a member of this political forum!");
        System.out.println();
    }
}
