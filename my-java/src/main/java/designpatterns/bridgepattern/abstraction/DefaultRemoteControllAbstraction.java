package designpatterns.bridgepattern.abstraction;

import designpatterns.bridgepattern.implementator.DefaultFunctionsImplementator;
import designpatterns.bridgepattern.implementator.FunctionsImplementator;

public abstract class DefaultRemoteControllAbstraction implements RemoteControl {
    private static final FunctionsImplementator DEFAULT_IMPLEMENATOR = new DefaultFunctionsImplementator();

    protected FunctionsImplementator getFunctionsImplementator() {
        return DEFAULT_IMPLEMENATOR;
    }

    @Override
    public void on() {
        getFunctionsImplementator().doOn();
    }

    @Override
    public void off() {
        getFunctionsImplementator().doOff();
    }
}
