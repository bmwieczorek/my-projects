package com.bawi.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestJavaRegexMatchingKeywords {
    
    
    @Test
    public void should() { 
        // given
        String text = "RCI+1A:2B3C4D+EY:5E6E7F+AF:XXXYYYZZZ";

        // when
        Pattern p = Pattern.compile("\\+([A-Z0-9]{2})\\:");
        Matcher m = p.matcher(text);

        // then
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    @Test
    public void should2() { 
        // given
        String text = "RCI+1A:2B3C4D+EY:5E6E7F+AF:XXXYYYZZZ";

        // when
        Pattern p = Pattern.compile("\\+([A-Z0-9]{2})\\:");
        Matcher m = p.matcher(text);

        // then
        while (m.find()) {
            System.out.println("carrier=" + m.group(1));
        }
    }

    @Test
    public void should3() { 
        // given
        String text = "RCI+1A:2B3C4D+EY:5E6E7F+AF:XXXYYYZZZ";

        // when
        Pattern p = Pattern.compile("\\+(\\w{2}):(\\w{6})");
        Matcher m = p.matcher(text);

        // then
        while (m.find()) {
            System.out.println("carrier=" + m.group(1) + ", locator=" + m.group(2));
        }
    }
}
