package calendar;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DatesAndCalendarTest {

    @Test
    public void testName() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date currentDate = calendar.getTime();
        System.out.println("Today 8:15:0,0 = " + currentDate);

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterdayDate = calendar.getTime();
        System.out.println("Yesterday 8:15:0,0 = " + yesterdayDate);

        calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        System.out.println("Today now = " + nowDate);

        calendar.add(Calendar.MINUTE, 10);
        Date inNext10minute = calendar.getTime();
        System.out.println("Today in next 10 minute from now = " + inNext10minute);
    }

}
