package designpatterns.adapterpattern.twosystems.adapter;

import static java.lang.Integer.valueOf;

import java.util.Calendar;
import java.util.Date;

public class PeselToBirthDateConverter {

    public Date convert(String pesel) {
        int year = valueOf("19" + pesel.substring(0, 2));
        int month = valueOf(pesel.substring(2, 4));
        int day = valueOf(pesel.substring(4, 6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        return date;
    }
}
