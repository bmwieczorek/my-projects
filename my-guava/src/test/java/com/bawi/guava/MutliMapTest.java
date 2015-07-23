package com.bawi.guava;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class MutliMapTest {
    @Test
    public void shouldParseMultimap() throws Exception {
        String text = "key1-value11/value12,key2-value21/value22";
        String[] entries = text.split(",");
        Multimap<String, String> multimap = LinkedListMultimap.create();
        for (String entry : entries) {
            String[] keyAndValues = entry.split("-");
            String key = keyAndValues[0];
            String[] values = keyAndValues[1].split("/");
            multimap.putAll(key, Arrays.asList(values));
        }
        System.out.println(multimap);
        
        Properties properties = new Properties();
        properties.load(new StringReader("key1=123\nkey2=567"));
        assertEquals("123", properties.get("key1"));
        assertEquals("567", properties.get("key2"));
    }

}
