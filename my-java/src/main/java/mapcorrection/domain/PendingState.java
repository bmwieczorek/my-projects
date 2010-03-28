package mapcorrection.domain;

public class PendingState implements State{
    public void approve(DO mcdo) {
        System.out.println("Approving pending mc: " + mcdo);
        mcdo.state = State.APPROVED;
        System.out.println("Approved pending mc: " + mcdo);
    }
    public void reject(DO mcdo) {
        System.out.println("Rejecting pending mc: " + mcdo);
        mcdo.state = State.REJECTED;
        System.out.println("Rejected pending mc: " + mcdo);
    }
    @Override
    public String toString() { return "pending"; }
}
