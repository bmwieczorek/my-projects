package statepattern.correct;

public class GumMachine {

    private State currentState;

    private final State noMoneyState;

    private final State hasMoneyState;

    private final State soldState;

    // etc

    public GumMachine() {
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        soldState = new SoldState(this);
        currentState = noMoneyState;
    }

    // external interfaces seen by client (user), their behavior is different
    // base on the machine state, e.g. the gum will be released only if the user
    // has paid and there is a gum available

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void reclaimMoney() {
    }

    public void releaseGum() {
        currentState.releaseGum();
    }

    // should not be exposed to the client but only to the state objects if they need to modify the state
    void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    State getCurrentState() {
        return currentState;
    }

    State getNoMoneyState() {
        return noMoneyState;
    }

    State getHasMoneyState() {
        return hasMoneyState;
    }

    State getSoldState() {
        return soldState;
    }

    public static void main(String[] args) {
        GumMachine gumMachine = new GumMachine();
        gumMachine.insertMoney();
        gumMachine.releaseGum();
    }

}
