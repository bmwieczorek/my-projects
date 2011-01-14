import org.apache.log4j.Logger;

public class MyService {

    private static final Logger LOGGER = Logger.getLogger(MyService.class);
    private static int i = 0;

    private MyDataSource myDataSource;

    public MyService() {
        LOGGER.debug("Created MyService !!!! " + i++);
    }

    public void print() {
        myDataSource.print();
    }

    public void setMyDataSource(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

}
