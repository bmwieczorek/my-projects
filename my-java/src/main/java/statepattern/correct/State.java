package statepattern.correct;

public interface State {
    void insertMoney();

    void reclaimMoney();

    public void releaseGum();
}
