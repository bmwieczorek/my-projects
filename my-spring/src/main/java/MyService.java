import org.apache.log4j.Logger;


public class MyService {
	
	private static int i = 0;
	
	private static Logger logger = Logger.getLogger(MyService.class);
	
	public MyService() {
		logger.debug("Created MyService !!!! " + i++);
	}

}
