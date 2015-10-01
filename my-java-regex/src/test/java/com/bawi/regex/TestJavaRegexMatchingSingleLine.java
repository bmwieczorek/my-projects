package com.bawi.regex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestJavaRegexMatchingSingleLine {

    private static final String WINDOWS_LINES = "first line\r\nsecond line \r\n third line";
    private static final String UNIX_LINES = "first line\rsecond line \r third line";

    @Test
    public void shouldNotFindMatching() throws Exception {
        assertFalse(Pattern.compile("^.*$").matcher(UNIX_LINES).find()); // dot '.' by default does not match \r not \r\n; anchors ^ and $ indicate begin and end of input string
        assertFalse(Pattern.compile("^.*$").matcher(WINDOWS_LINES).find());
    }

    @Test
    public void shouldFindWindowsLines() throws Exception {
        shouldFindLinesUsingMultiLineMode(WINDOWS_LINES);
    }

    @Test
    public void shouldFindUnixLines() throws Exception {
        shouldFindLinesUsingMultiLineMode(UNIX_LINES);
    }

    private void shouldFindLinesUsingMultiLineMode(String multiLineText) {
        Matcher m = Pattern.compile("(?m)^.*$").matcher(multiLineText); // (?m) MULTI-LINE mode - when explicitly enabled - makes anchors ^ and $ indicate begin and end of line
        List<String> lines = new ArrayList<>();
        while (m.find()) {
            lines.add(m.group());
            System.out.println("line = '" + m.group() + "'");
        }
        assertEquals(3, lines.size());
        assertEquals("first line", lines.get(0));
        assertEquals("second line ", lines.get(1));
        assertEquals(" third line", lines.get(2));
    }

    @Test
    public void shouldFindWindowsMultiLine() throws Exception {
        shouldFindLinesUsingSingleLineMode(WINDOWS_LINES);
    }

    @Test
    public void shouldFindLinuxMultiLine() throws Exception {
        shouldFindLinesUsingSingleLineMode(UNIX_LINES);
    }

    private void shouldFindLinesUsingSingleLineMode(String multiLineText) {
        Matcher m = Pattern.compile("(?s)^.*$").matcher(multiLineText); // (?s) SINGLE-LINE mode - when explicitly enabled - makes dot '.' match new line chars
        List<String> lines = new ArrayList<>();
        while (m.find()) {
            lines.add(m.group());
            System.out.println("line = '" + m.group() + "'");
        }
        assertEquals(1, lines.size());
        assertEquals(multiLineText, lines.get(0));
    }

}
