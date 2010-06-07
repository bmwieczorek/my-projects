package designpatterns.adapterpattern.classadapter;

import designpatterns.adapterpattern.Adaptee;

public class ClassAdapter extends Adaptee implements Target {

    public void request() {
        makeRequest();
    }

}
