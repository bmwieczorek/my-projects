package mapcorrection.domain;

public interface State {
    final static State PENDING = new PendingState();
    final static State APPROVED = new ApprovedState();
    final static State REJECTED = new RejectedState();

    void approve(DO mcdo);

    void reject(DO mcdo);
}
