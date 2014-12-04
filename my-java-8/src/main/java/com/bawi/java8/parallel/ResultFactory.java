package com.bawi.java8.parallel;

import java.util.Date;
import java.util.Map;
import java.util.function.Predicate;

import com.bawi.java8.parallel.Result.Tps;
import com.bawi.java8.stats.Stats;
import com.bawi.java8.stats.StatsCollector;

public class ResultFactory {
    
    private enum TpsCalculationMode { ALL_PERIODS, WITHOUT_LAST_PERIOD; };

    Result createResult(MutableAccumulation mutableAccumulation) {
        return new Result(
                mutableAccumulation.getUniqueEmdNumbers(), 
                mutableAccumulation.getCount(), 
                createTps(mutableAccumulation, TpsCalculationMode.ALL_PERIODS), 
                createTps(mutableAccumulation, TpsCalculationMode.WITHOUT_LAST_PERIOD)
               );
    }

    private Tps createTps(MutableAccumulation mutableAccumulation, TpsCalculationMode tpsCalculationMode) {
        Date lastPeriodStartDate = mutableAccumulation.getLastPeriodStartDate();
        Map<Date, Period> periods = mutableAccumulation.getPeriods();
        Predicate<? super Date> filterCriteria = 
                date -> TpsCalculationMode.ALL_PERIODS.equals(tpsCalculationMode) ? 
                        date.compareTo(lastPeriodStartDate) <= 0 : date.compareTo(lastPeriodStartDate) < 0;

        Stats stats = periods.keySet().
                parallelStream().
                filter(filterCriteria).
                map(date -> (double)periods.get(date).getDatesCount()/(double)60).
                collect(new StatsCollector());

        return new Result.Tps(stats.getMin(), stats.getMax(), stats.getAvg());
    }

}
