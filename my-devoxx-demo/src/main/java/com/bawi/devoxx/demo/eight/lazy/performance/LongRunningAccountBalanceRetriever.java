package com.bawi.devoxx.demo.eight.lazy.performance;

public class LongRunningAccountBalanceRetriever {

    public Double getAccountBallance(int id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Double.valueOf(999999 + id);
    }

}
