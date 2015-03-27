package com.bawi.eventsfilter;

public class EventsFilter {

    private ThresholdReachedHandler thresholdReachedHandler; // NOPMD
    private ThresholdEvaluationHandler thresholdEvaluationHandler;
    private EventsStorage eventsStorage;
    private EventsFactory eventsFactory;

    public static class Builder {
        private final EventsFilter eventsFilter = new EventsFilter();

        public Builder(ThresholdReachedHandler thresholdReachedHandler) {
            eventsFilter.thresholdReachedHandler = thresholdReachedHandler;
        }

        public Builder withThresholdEvaluationHandler(ThresholdEvaluationHandler thresholdEvaluationHandler) {
            eventsFilter.thresholdEvaluationHandler = thresholdEvaluationHandler;
            return this;
        }

        public Builder withEventsCountThreshold(int eventsCountThreshold) {
            eventsFilter.thresholdEvaluationHandler = new EventsCountThresholdEvaluationHandler(eventsCountThreshold);
            return this;
        }

        public Builder withEventsStorage(EventsStorage eventsStorage) {
            eventsFilter.eventsStorage = eventsStorage;
            return this;
        }

        public Builder withEventsFactory(EventsFactory eventsFactory) {
            eventsFilter.eventsFactory = eventsFactory;
            return this;
        }

        public EventsFilter build() {
            return eventsFilter;
        }

    }

    public static Builder createBuilder(ThresholdReachedHandler thresholdReachedHandler) {
        return new Builder(thresholdReachedHandler);
    }

    public void addEvent(Object object) {
        Event event = eventsFactory.createEvent(object);
        eventsStorage.addEvent(event);

        if (thresholdEvaluationHandler.isThresholdReached(eventsStorage)) {
            thresholdReachedHandler.onThresholdReached(event);
        }
    }

}
