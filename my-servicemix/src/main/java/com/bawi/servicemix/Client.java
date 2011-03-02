package com.bawi.servicemix;

public class Client {

    private Provider provider;

    public Client() {
        System.out.println("Client created");
    }

    public void init() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Hello World: setter-> " + provider.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
