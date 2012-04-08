package com.sabre.ticketing.common.eventsfilter;



public class EventsCountThresholdEvaluationHandler implements ThresholdEvaluationHandler {

    private static final int DEFAULT_EVNETS_COUNT_THRESHOLD = 3;
    private final int eventsCountThreshold;

    public EventsCountThresholdEvaluationHandler() {
        this(DEFAULT_EVNETS_COUNT_THRESHOLD);
    }

    public EventsCountThresholdEvaluationHandler(int eventsCountThreshold) {
        this.eventsCountThreshold = eventsCountThreshold;
    }

    @Override
    public boolean isThresholdReached(EventsStorage eventsStorage) {
        return eventsStorage.getEventsCount() >= eventsCountThreshold;
    }
}
