package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

public class CalendarTest {

    @Test
    public void shouldCreateXmlGregorianCallendar() throws DatatypeConfigurationException {

        // given
        System.setProperty("user.timezone", "America/Chicago");
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 10, 28, 12, 1, 0);
        cal.set(Calendar.MILLISECOND, 100);
        cal.setTimeZone(TimeZone.getTimeZone("CDT"));

        // when
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar.setTime(cal.getTime());
        XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        xmlCal.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);

        // then
        System.out.println(xmlCal.toXMLFormat());
    }
}
