package mapcorrection.domain;

public class ApprovedState implements State {
    @Override
    public void approve(DO mcdo) {
        throw new UnsupportedOperationException("Cannot approve already approved mc: " + mcdo);
    }

    @Override
    public void reject(DO mcdo) {
        System.out.println("Rejecting approved mc: " + mcdo);
        mcdo.state = State.REJECTED;
        System.out.println("Rejected approved mc: " + mcdo);
    }

    @Override
    public String toString() {
        return "approved";
    }
}
