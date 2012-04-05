package com.sabre.ticketing.common.eventsfilter;

public interface ThresholdEvaluationHandler {

    boolean isThresholdReached(EventsStorage eventsStorage);

}
