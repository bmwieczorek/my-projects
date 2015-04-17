package date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class DateTest {

    @Test
    public void test() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(new SimpleDateFormat().format(date));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(date));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(date));
        System.out.println(new SimpleDateFormat("EEEEEE, d MMMMM yyyy HH:mm:ss Z", Locale.US).format(date));
        System.out.println(new SimpleDateFormat("EEEEEE, d MMMMM yyyy HH:mm:ss Z", new Locale("pl", "PL")).format(date));
    }

}
