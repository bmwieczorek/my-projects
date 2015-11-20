package statepattern.correct;

public class SoldState implements State {

    private GumMachine gumMachine;

    public SoldState(GumMachine gumMachine) {
        this.gumMachine = gumMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Insterting money for " + gumMachine);
    }

    @Override
    public void reclaimMoney() {
        System.out.println("Reclaiming money for " + gumMachine);
    }

    @Override
    public void releaseGum() {
        System.out.println("Reclaiming gum for " + gumMachine);
    }

}
