import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;


public class MyLocale {
    public static void main(String[] args) {
        Locale locale = new Locale("pl","PL");
        Date now = new Date();
        String s = DateFormat.getDateInstance(DateFormat.SHORT, locale).format(now);
        String s2 = NumberFormat.getNumberInstance(locale).format(1822.75);
        System.out.println(s+"::"+s2);
    }
    
}
