package mapcorrection.domain;

public class RejectedState implements State {
    public void approve(DO mcdo) {
        System.out.println("Approving rejected mc: " + mcdo);
        mcdo.state = State.APPROVED;
        System.out.println("Approved rejected mc: " + mcdo);
    }

    public void reject(DO mcdo) {
        throw new UnsupportedOperationException("Cannot reject already rejected mc: " + mcdo);
    }

    @Override
    public String toString() {
        return "rejected";
    }
}
