package com.bawi.java8.parallel;

import java.util.Set;

public class Result {
    public static class Tps {
        private final double min, max, avg;

        public Tps(double min, double max, double avg) {
            this.min = min;
            this.max = max;
            this.avg = avg;
        }

        @Override
        public String toString() {
            return "Tps [min=" + min + ", max=" + max + ", avg=" + avg + "]";
        }
    }

    private final Set<Long> uniqueEmdNumbers;
    private final int count;
    private final Tps allPeriodsTps;
    private final Tps withoutLastPeriodTps;

    public Result(Set<Long> uniqueEmdNumbers, int count, Tps allPeriodsTps, Tps excludedLastPeriodTps) {
        this.uniqueEmdNumbers = uniqueEmdNumbers;
        this.count = count;
        this.allPeriodsTps = allPeriodsTps;
        this.withoutLastPeriodTps = excludedLastPeriodTps;
    }

    @Override
    public String toString() {
//        return "EmdStatsResult [uniqueEmdNumbers=" + uniqueEmdNumbers + ", count=" + count + ", allPeriodsTps=" + allPeriodsTps
//                + ", withoutLastPeriodTps=" + withoutLastPeriodTps + "]";
        return "EmdStatsResult [uniqueEmdNumbers count=" + uniqueEmdNumbers.size() + ", count=" + count + ", allPeriodsTps=" + allPeriodsTps
                + ", withoutLastPeriodTps=" + withoutLastPeriodTps + "]";
    }

}