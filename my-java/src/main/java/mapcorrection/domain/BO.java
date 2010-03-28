package mapcorrection.domain;

public class BO{
    DO mcdo;
    DAO dao;
    public BO(DO mcdo,DAO dao) {
        this.dao = dao;
        this.mcdo = mcdo;
    }
    
    public void approve(){
        mcdo.approve();
    }
    public void reject(){
        mcdo.reject();
    }

    public void save(){
        dao.save(mcdo);
    }
}
