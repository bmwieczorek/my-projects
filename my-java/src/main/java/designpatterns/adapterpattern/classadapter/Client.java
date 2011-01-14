package designpatterns.adapterpattern.classadapter;

public class Client {
    private Target target;

    public Client(Target target) {
        this.target = target;
    }

    void performRequest() {
        target.request();
    }

    public static void main(String[] args) {
        // client is completely decoupled from adaptee
        Target target = new ClassAdapter();
        Client client = new Client(target);
        client.performRequest();
    }
}
