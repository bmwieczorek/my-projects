package statepattern.correct;

public class HasMoneyState implements State {
    private GumMachine gumMachine;

    public HasMoneyState(GumMachine gumMachine) {
        this.gumMachine = gumMachine;
    }

    public void insertMoney() {
    }

    public void reclaimMoney() {
    }

    public void releaseGum() {
        System.err.println(this + ":" + gumMachine.getCurrentState());
        System.out.println("Here is your gum!");
        gumMachine.setCurrentState(gumMachine.getNoMoneyState());
        System.err.println(this +":" +gumMachine.getCurrentState());
    }
}