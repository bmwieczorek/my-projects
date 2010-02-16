import org.apache.log4j.Logger;

public class MyDataSourceImpl implements MyDataSource {

	private static Logger logger = Logger.getLogger(MyDataSourceImpl.class);

	@Override
	public void print() {
		logger.debug("!!!! Impl !!!!");
	}

}
