package designpatterns.bridgepattern;

import designpatterns.bridgepattern.abstraction.SimpleRemoteControl;
import designpatterns.bridgepattern.abstraction.SonyRemoteControl;

public class Example {

    public static void main(String[] args) {
        SimpleRemoteControl simple = new SimpleRemoteControl();
        simple.on();
        simple.off();
        SonyRemoteControl sony = new SonyRemoteControl();
        sony.on();
        sony.off();
        sony.lightOnOff();
    }

}
