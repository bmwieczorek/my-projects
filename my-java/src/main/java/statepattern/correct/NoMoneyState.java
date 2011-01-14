package statepattern.correct;

public class NoMoneyState implements State {
    private GumMachine gumMachine;

    public NoMoneyState(GumMachine gumMachine) {
        this.gumMachine = gumMachine;
    }

    @Override
    public void insertMoney() {
        System.err.println(this + ":" + gumMachine.getCurrentState());
        System.out.println(this + ":" + "Money received");
        gumMachine.setCurrentState(gumMachine.getHasMoneyState());
        System.err.println(this + ":" + gumMachine.getCurrentState());
    }

    @Override
    public void reclaimMoney() {
    }

    @Override
    public void releaseGum() {
    }
}
