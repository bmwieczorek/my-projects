package designpatterns.chainofresponsibility.copy;

import java.util.Stack;

enum MailType {
    HAPPY, SAD, SPAM, OTHER;
}

class Mail {
    MailType mailType;

    public Mail(MailType mailType) {
        this.mailType = mailType;
    }
}

abstract class AbstractHander {
    AbstractHander successorHandler;

    abstract void handle(Mail mail);
}

class CatchAllHandler extends AbstractHander {
    public void handle(Mail mail) {
        System.out.println(getClass().getName() + " received non-handleable " + mail.mailType);
    }
}

class HappyHandler extends AbstractHander {

    public void handle(Mail mail) {
        if (MailType.HAPPY == mail.mailType)
            System.out.println(getClass().getName() + " handling " + mail.mailType + " to ceo");
        else {
            System.out.println(getClass().getName() + " forwarding " + mail.mailType + " ...");
            successorHandler.handle(mail);
        }
    }
}

class SadHandler extends AbstractHander {

    public void handle(Mail mail) {
        if (MailType.SAD == mail.mailType)
            System.out.println(getClass().getName() + " handling " + mail.mailType + " to legal dept");
        else {
            System.out.println(getClass().getName() + " forwarding " + mail.mailType + " ...");
            successorHandler.handle(mail);
        }
    }
}

class SpamHandler extends AbstractHander {

    public void handle(Mail mail) {
        if (MailType.SPAM == mail.mailType) {
            System.out.println(getClass().getName() + " handling " + mail.mailType + " to trash");
        } else {
            System.out.println(getClass().getName() + " forwarding " + mail.mailType + " ...");
            successorHandler.handle(mail);
        }
    }
}

public class Example {

    public static void main(String[] args) {
        Mail mail = new Mail(MailType.HAPPY);
        Stack<AbstractHander> handlers = new Stack<AbstractHander>();
        handlers.push(new HappyHandler());
        handlers.push(new SadHandler());
        handlers.push(new SpamHandler());

        AbstractHander lastHandler;
        AbstractHander currectHandler = new CatchAllHandler();
        while (!handlers.isEmpty()) {
            lastHandler = currectHandler;
            currectHandler = handlers.pop();
            currectHandler.successorHandler = lastHandler;
        }
        currectHandler.handle(mail);
    }

}
