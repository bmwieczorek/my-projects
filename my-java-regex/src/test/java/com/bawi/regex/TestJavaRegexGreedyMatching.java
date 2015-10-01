package com.bawi.regex;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestJavaRegexGreedyMatching {

    private static final String TEXT = "ABC'TIF+DEF'GHI+JKL'TIF+MNO'PRQ";

    @Test
    public void shouldReturnTheShortestMatchingString() {
        // when
        List<String> results = getMatchingStringsInTextForRegex(TEXT, "TIF\\+.*?\\+");
        
        // then
        assertEquals(1, results.size());
        assertEquals("TIF+DEF'GHI+", results.get(0));
    }

    
    @Test
    public void shouldReturnTheLongestMatchingString() {
        // when
        List<String> results = getMatchingStringsInTextForRegex(TEXT, "TIF\\+.*\\+"); // no '?' in regex
        
        // then
        assertEquals(1, results.size());
        assertEquals("TIF+DEF'GHI+JKL'TIF+", results.get(0));
    }
    
    private List<String> getMatchingStringsInTextForRegex(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> results = new ArrayList<>();
        while(matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }

}
