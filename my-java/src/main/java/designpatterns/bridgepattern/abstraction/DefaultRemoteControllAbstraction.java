package designpatterns.bridgepattern.abstraction;

import designpatterns.bridgepattern.implementator.FunctionsImplementator;
import designpatterns.bridgepattern.implementator.DefaultFunctionsImplementator;

public abstract class DefaultRemoteControllAbstraction implements RemoteControl {
    private static final FunctionsImplementator DEFAULT_IMPLEMENATOR = new DefaultFunctionsImplementator();

    protected FunctionsImplementator getFunctionsImplementator() {
        return DEFAULT_IMPLEMENATOR;
    }

    public void on() {
        getFunctionsImplementator().doOn();
    };

    public void off() {
        getFunctionsImplementator().doOff();
    };
}
