import java.util.Calendar;
import java.util.Date;

public class CalendarVsDate {

	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d.getTime());

		Calendar c = Calendar.getInstance();
		// c.add(Calendar.DATE, 5);

		c.setTimeInMillis(d.getTime());

		System.out.println(c.getTimeInMillis());
	}
}
