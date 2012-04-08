package com.sabre.ticketing.common.eventsfilter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.sabre.ticketing.common.eventsfilter.impl.DefaultEventsFactory;
import com.sabre.ticketing.common.eventsfilter.impl.TimeExpiringEventsStorage;

public class EventsFilterTest {

    private final ThresholdReachedHandler thresholdReachedHandlerMock = mock(ThresholdReachedHandler.class);
    private final Time time = new Time();
    private final EventsFactory eventsFactory = new DefaultEventsFactory(time);
    private final EventsStorage timeExpiringEventsStorage = new TimeExpiringEventsStorage(new DefaultEventsStorage(),
            time);

    @Test
    public void shouldNotCallTRHandlerForFewerEventsThatThreshold() {
        int eventsCountThreshold = 3;
        int numberOfEvents = 2;

        // given
        EventsFilter filter = createEventsFilterWithEventCountThreshold(eventsCountThreshold);

        // when
        for (int i = 0; i < numberOfEvents; i++) {
            filter.addEvent(new IllegalArgumentException());
        }

        // then
        verify(thresholdReachedHandlerMock, times(0)).onThresholdReached(any(Event.class));
    }

    @Test
    public void shouldCallOnceTRHandlerWhenEventsCountMeetThreshold() {
        int eventsCountThreshold = 3;
        int numberOfEvents = 3;

        // given
        EventsFilter filter = createEventsFilterWithEventCountThreshold(eventsCountThreshold);

        // when
        for (int i = 0; i < numberOfEvents; i++) {
            filter.addEvent(new IllegalArgumentException());
        }

        // then
        verify(thresholdReachedHandlerMock, times(1)).onThresholdReached(any(Event.class));
    }

    @Test
    public void shouldCallTRHandlerForEveryEventAboveOrEqualThreshold() {
        int eventsCountThreshold = 3;
        int numberOfEvents = 4;

        // given
        EventsFilter filter = createEventsFilterWithEventCountThreshold(eventsCountThreshold);

        // when
        for (int i = 0; i < numberOfEvents; i++) {
            filter.addEvent(new IllegalArgumentException());
        }

        // then
        verify(thresholdReachedHandlerMock, times(2)).onThresholdReached(any(Event.class));
    }

    @Test
    public void shouldNotCallTRHandlerWhenEventsArriveVerySeldomAndExpire() {
        int eventsCountThreshold = 2;
        int numberOfEvents = 4;

        // given
        EventsFilter filter = createEventsFilterWithEventCountThreshold(eventsCountThreshold);

        // when
        for (int i = 0; i < numberOfEvents; i++) {
            filter.addEvent(new IllegalArgumentException());
            time.addTimeMillis(2 * TimeExpiringEventsStorage.DEFAULT_TTL_MILLIS);
        }

        // then
        verify(thresholdReachedHandlerMock, times(0)).onThresholdReached(any(Event.class));
    }

    private EventsFilter createEventsFilterWithEventCountThreshold(int eventsCountThreshold) {
        // @formatter:off
        return EventsFilter
                .createBuilder(thresholdReachedHandlerMock)
                .withEventsStorage(timeExpiringEventsStorage)
                .withEventsCountThreshold(eventsCountThreshold)
                .withEventsFactory(eventsFactory)
                .build();
        // @formatter:on
    }
}
