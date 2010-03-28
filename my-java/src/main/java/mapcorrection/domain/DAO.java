package mapcorrection.domain;

public interface DAO{
    public void save(DO mcdo);
    public DO loadById(int id);
}
