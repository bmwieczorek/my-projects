import org.apache.log4j.Logger;

public class MyDataSourceMock implements MyDataSource {

    private static Logger logger = Logger.getLogger(MyDataSourceMock.class);

    @Override
    public void print() {
        logger.debug("!!!! Mock !!!!");
    }
}
