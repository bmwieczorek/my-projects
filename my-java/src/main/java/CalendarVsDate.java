import java.util.Calendar;
import java.util.Date;


public class CalendarVsDate {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Date d = new Date();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Calendar c = Calendar.getInstance();
        //c.add(Calendar.DATE, 5);
        
        System.out.println(d.getTime());
        //System.out.println(c.getTimeInMillis());
        
        c.setTimeInMillis(d.getTime());
        
        System.out.println(c.getTimeInMillis());

    }

}
