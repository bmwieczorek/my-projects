package designpatterns.adapterpattern.objectadapter;

import designpatterns.adapterpattern.Adaptee;

public class Client {
    private TargetClass target;

    void performRequest() {
        target.request();
    }

    public Client(TargetClass target) {
        this.target = target;
    }

    public static void main(String[] args) {
        // client is coupled to adaptee (existing class)
        Adaptee adaptee = new Adaptee();
        TargetClass target = new ObjectAdapter(adaptee);
        Client client = new Client(target);
        client.performRequest();
    }
}
