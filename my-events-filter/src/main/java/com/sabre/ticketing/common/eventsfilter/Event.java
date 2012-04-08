package com.sabre.ticketing.common.eventsfilter;

public class Event {

    private final long id;

    private final Object object;

    private final Time creationTime;

    public Event(long id, Object object, Time time) {
        this.id = id;
        this.object = object;
        this.creationTime = new Time(time.getTimeMillis());
    }

    public Object getObject() {
        return object;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    // CHECKSTYLE:OFF

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @SuppressWarnings({ "PMD.IfStmtsMustUseBraces" })
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", object=" + object + ", creationTime=" + creationTime + "]";
    }

    // CHECKSTYLE:ON

}
