package mapcorrection;
import java.util.HashMap;
import java.util.Map;

import mapcorrection.domain.BO;
import mapcorrection.domain.DAO;
import mapcorrection.domain.DO;
import mapcorrection.domain.Type;

public class Example {
    
    public static void main(String[] args) {    
        
        DAO dao = new DAO() {
            Map<Integer,DO> mcdos = new HashMap<Integer, DO>();
            int id = 0;
            public void save(DO mcdo) {
                mcdos.put(id++, mcdo);
                System.out.println("saved" + mcdo);
            }
            
            public DO loadById(int id) {
                return mcdos.get(id);
            }
        };
        DO mcdo = new DO("mc1",Type.MODIFY_STREET_NAME);
        System.out.println(mcdo);
        BO bo = new BO(mcdo, dao);
        bo.approve();
        bo.save();
        DO newMcdo = dao.loadById(0);
        System.out.println(newMcdo);
        BO newMcbo = new BO(newMcdo, dao);
        newMcbo.reject();
        newMcbo.save();
        DO newMcdo2 = dao.loadById(1);
        System.out.println(newMcdo2);
        
    }
}
