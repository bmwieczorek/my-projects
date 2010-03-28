package designpatterns.chainofresponsibility;


enum MailType {
    HAPPY, SAD, SPAM, OTHER;
}

class Mail {
    MailType mailType;

    public Mail(MailType mailType) {
        this.mailType = mailType;
    }
}

interface Handler {
    void handle(Mail mail);
}

class CatchAllHandler implements Handler {
    public void handle(Mail mail) {
        System.out.println(getClass().getName() + " received non-handleable "
                + mail.mailType);
    }
}

class HappyHandler implements Handler {
    Handler successorHandler;

    public HappyHandler(Handler handler) {
        this.successorHandler = handler;
    }

    public void handle(Mail mail) {
        if (MailType.HAPPY == mail.mailType)
            System.out.println(getClass().getName() + " handling "
                    + mail.mailType + " to ceo");
        else {
            System.out.println(getClass().getName() + " forwarding "
                    + mail.mailType + " ...");
            successorHandler.handle(mail);
        }
    }
}

class SadHandler implements Handler {
    Handler successorHandler;

    public SadHandler(Handler handler) {
        this.successorHandler = handler;
    }

    public void handle(Mail mail) {
        if (MailType.SAD == mail.mailType)
            System.out.println(getClass().getName() + " handling "
                    + mail.mailType + " to legal dept");
        else {
            System.out.println(getClass().getName() + " forwarding "
                    + mail.mailType + " ...");
            successorHandler.handle(mail);
        }
    }
}

class SpamHandler implements Handler {
    Handler successorHandler;

    public SpamHandler(Handler handler) {
        this.successorHandler = handler;
    }

    public void handle(Mail mail) {
        if (MailType.SPAM == mail.mailType) {
            System.out.println(getClass().getName() + " handling "
                    + mail.mailType + " to trash");
        } else {
            System.out.println(getClass().getName() + " forwarding "
                    + mail.mailType + " ...");
            successorHandler.handle(mail);
        }
    }
}

public class Example {

    public static void main(String[] args) {
        Mail mail = new Mail(MailType.OTHER);
        Handler handler =
                new SpamHandler(new SadHandler(new HappyHandler(
                        new CatchAllHandler())));
        handler.handle(mail);
    }
}
