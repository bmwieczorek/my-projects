package mediator;

/**
 * @author dheaton
 */
public class Client {

    public static void main(String args[]) {

        // forum is a mediator
        Forum forum = new PoliticalForum();

        // colleagues are aware of the forum but don't have direct dependency do other colleagues
        PrimeMinister pm = new PrimeMinister("Paul Martin");
        MemberOfParliament cp = new MemberOfParliament("Carolyn Parrish");
        MemberOfParliament gd = new MemberOfParliament("Gilles Duceppe");

        // register colleagues in the forum (mediator)
        forum.add(pm);
        forum.add(cp);
        forum.add(gd);
        System.out.println();

        // the send method delegates the call to the mediator which looks up the receiver if registered,
        // and forwards the email do him/her
        pm.send("Hello 3very0ne!!!!1!");
        cp.send("Paul Martin", "Grr.");
        cp.send("George Bush", "Grr.");
        pm.send("Carolyn Parrish", "Jabberwocky.");
        gd.send("Oui!");
    }

}
