package com.bawi.eventsfilter;

public interface ThresholdEvaluationHandler {

    boolean isThresholdReached(EventsStorage eventsStorage);

}
