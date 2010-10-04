package designpatterns.adapterpattern.twosystems.adapter;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class PeselToBirthDateConverterTest {

    private PeselToBirthDateConverter converter = new PeselToBirthDateConverter();

    @SuppressWarnings("deprecation")
    @Test
    public void testConvert() {
        // given
        String pesel = "82081412345";

        // when
        Date date = converter.convert(pesel);

        // then
        assertEquals(date.getDate(), 14);
        assertEquals(date.getMonth(), 8);
        assertEquals(date.getYear(), 82);
    }

}
