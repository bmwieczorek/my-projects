package clean.code;

@SuppressWarnings("unused") 
public class DataStore {

    public DataStore(boolean isOracle, String user, String password, String url) {
        if (isOracle) {
            // connect to oracle
        } else {
            // connect to mysql
        }
    }

    public DataStoreConfigurationQueryResult executeQuery(String string) {
        return null;
    }

    public void connect() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        
    }

}
