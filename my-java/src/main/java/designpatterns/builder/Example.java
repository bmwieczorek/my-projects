package designpatterns.builder;

import designpatterns.builder.Holidays.HolidayBuilder;

class Holidays {
    private int numberOfNights;
    private boolean isBreakfast;

    private Holidays() {
    }

    @Override
    public String toString() {
        return numberOfNights + ":" + isBreakfast;
    }

    public static class HolidayBuilder {
        int builderNumberOfNights;
        boolean builderIsBreakFast;

        public HolidayBuilder(int numberOfNights) {
            this.builderNumberOfNights = numberOfNights;
        }

        public HolidayBuilder addBreakfast() {
            builderIsBreakFast = true;
            return this;
        }

        public Holidays build() {
            Holidays holidays = new Holidays();
            holidays.numberOfNights = builderNumberOfNights;
            holidays.isBreakfast = builderIsBreakFast;
            return holidays;
        }

    }
}

public class Example {

    public static void main(String[] args) {
        Holidays holidays = new HolidayBuilder(2).addBreakfast().build();
        System.out.println(holidays);
        Holidays build = new HolidayBuilder(10).build();
        System.out.println(build);

    }
}
