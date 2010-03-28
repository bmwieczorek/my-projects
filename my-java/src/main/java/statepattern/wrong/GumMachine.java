package statepattern.wrong;

public class GumMachine {

    enum State {
        NoMoney, HasMoney, Sold, SoldOut
    }

    private State currentState;

    public GumMachine() {
        currentState = State.NoMoney;
    }

    // external interfaces seen by client (user), their behavior is different
    // base on the machine state, e.g. the gum will be released only if the user
    // has paid and there is a gum available

    public void onInsertMoney() {
        switch (currentState) {
            case NoMoney:
                System.out.println("Money received");
                currentState = State.HasMoney;
                break;
            case HasMoney:
                System.out.println("Wait, you still have some money left!");
                break;
            case Sold:
                System.out.println("In process ...");
                break;
            case SoldOut:
                System.out.println("Sorry, no gums available");
            default:
                break;
        }
    }

    public void onReclaimMoney() {
        // the same ifs for other state transitions
    }

    public void onReleaseGum() {
        // the same ifs for other state transitions
        if (currentState == State.HasMoney) {
            System.out.println("Here is your gum!");
            currentState = State.Sold;
        }
    }

    public static void main(String[] args) {
        GumMachine gumMachine = new GumMachine();
        gumMachine.onInsertMoney();
        gumMachine.onReleaseGum();
    }
}
