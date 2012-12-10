package com.bawi.datacollector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

public class DataCollector {

    private static Map<String, List<Event>> map = new TreeMap<String, List<Event>>();

    private static class Event {
        private String name;
        private String address;

        public Event(String name, String address) {
            this.name = name;
            this.address = address;
        }

        @Override
        public String toString() {
            return "['" + name + "','" + address + "']";
        }

    }

    public static void main(String[] args) throws IOException {
        List<String> readLines = FileUtils.readLines(new File("src/main/resources/msze-krakow.txt"));
        for (String line : readLines) {
            if (line != null && line.length() > 0) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String address = parts[1].trim();
                int ndIdx = line.indexOf("nd.");
                String hours = line.substring(ndIdx + "nd.".length());
                String[] hourParts = hours.split(",");
                for (String hour : hourParts) {
                    @SuppressWarnings("unused")
                    String extraInfo = ""; 
                    int extraInfoStartIdx = hour.indexOf('(');
                    int extraInfoStopIdx = hour.indexOf(')');
                    if (extraInfoStartIdx > 0){
                        if (extraInfoStopIdx < extraInfoStartIdx){
                            System.out.println(name);
                        }
                        extraInfo = hour.substring(extraInfoStartIdx + 1, extraInfoStopIdx);
                        
                        hour = hour.substring(0, extraInfoStartIdx);
                    }
                    hour = hour.trim();
                    int hourIdx = hour.indexOf(".");
                    if (hourIdx < 0){
                        hour = hour + ":00";
                    } else {
                        hour = hour.replace('.', ':');
                    }
                    hour = "'" + hour + "'";
                    if (!map.containsKey(hour)) {
                        map.put(hour, new ArrayList<Event>());
                    }
                    List<Event> list = map.get(hour);
                    list.add(new Event(name, address));
                }
            }
        }
        System.out.println(map.keySet());
        String mapAsString = map.toString();
        System.out.println(mapAsString.replaceAll("=", " : ").replaceAll("']], '", "']]," + System.getProperty("line.separator") + "'"));
    }
}
