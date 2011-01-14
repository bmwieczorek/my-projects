import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleExample {
    public static void main(String[] args) {

        Locale locale = new Locale("pl", "PL");

        String formattedNumber = NumberFormat.getNumberInstance(locale).format(1822.75);
        System.out.println(formattedNumber);

        String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT, locale).format(new Date());
        System.out.println(formattedDate);

    }
}
