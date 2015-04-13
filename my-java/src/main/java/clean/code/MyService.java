package clean.code;


public class MyService {
    private Configuration configuration;
    private DataStore dataStoreConnection;

    public static void main(String[] args) {
        MyService myService = new MyService();
        myService.initialize();
        myService.start();
        // waiting to process requests by process method
    }
    
    private void start() {
        System.out.println("Running application");
    }

    private void initialize() {
        configuration = ConfigurationConverter.convert(dataStoreConnection.executeQuery("some query to get the configuration"));
    }

    public MyService() {
        DataStore dataStore = new DataStore(true, "me", "mypass", "myurl");
        dataStore.connect();
    }

    public Response process(Request request) {
        String user = request.getUser();
        configuration.isAllowed(user);
        // ignore response content for now
        return null; 
    }

}
