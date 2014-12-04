package com.bawi.java8;

import java.util.Set;

public class EmdStatsResult {
    private final Set<Long> uniqueEmdNumbers;
    private final int count;
    public EmdStatsResult(int count, Set<Long> uniqueEmdNumbers) {
        this.count = count;
        this.uniqueEmdNumbers = uniqueEmdNumbers;
    }
    public Set<Long> getUniqueEmdNumbers() {
        return uniqueEmdNumbers;
    }
    public int getCount() {
        return count;
    }
    @Override
    public String toString() {
        return "EmdStatsResult [unique emd numbers count=" + uniqueEmdNumbers.size() + ", count=" + count + "]";
    }
}