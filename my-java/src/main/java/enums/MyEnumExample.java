package enums;

import java.util.HashMap;
import java.util.Map;

enum City {
    Amsterdam("NL"), Krakow("PL");

    private static Map<String, City> countryCodeToCity = new HashMap<String, City>();
    static {
        City[] values = City.values();
        for (City city : values) {
            countryCodeToCity.put(city.countryCode, city);
        }
    }
    private String countryCode;

    City(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static City getByCountryCode(String countryCode) {
        return countryCodeToCity.get(countryCode);
    }

}

public class MyEnumExample {

    public static void main(String[] args) {
        System.out.println(City.Amsterdam.getCountryCode());
        System.out.println(City.getByCountryCode("PL"));
    }

}
