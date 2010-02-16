import org.apache.log4j.Logger;


public class MyService {
	
	private static int i = 0;
	
	private MyDataSource myDataSource; 
	
	private static Logger logger = Logger.getLogger(MyService.class);
	
	public MyService() {
		logger.debug("Created MyService !!!! " + i++);
	}
	
	public void print(){
		myDataSource.print();
	}

	public void setMyDataSource(MyDataSource myDataSource) {
		this.myDataSource = myDataSource;
	}

}
