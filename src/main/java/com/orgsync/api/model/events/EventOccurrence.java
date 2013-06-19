package com.orgsync.api.model.events;

import java.util.Date;

public class EventOccurrence {

    private Date startsAt;
    private Date endAt;
    private boolean isAllDay;

    public final Date getStartsAt() {
        return startsAt;
    }

    public final Date getEndAt() {
        return endAt;
    }

    public final boolean isAllDay() {
        return isAllDay;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endAt == null) ? 0 : endAt.hashCode());
        result = prime * result + (isAllDay ? 1231 : 1237);
        result = prime * result + ((startsAt == null) ? 0 : startsAt.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventOccurrence other = (EventOccurrence) obj;
        if (endAt == null) {
            if (other.endAt != null)
                return false;
        } else if (!endAt.equals(other.endAt))
            return false;
        if (isAllDay != other.isAllDay)
            return false;
        if (startsAt == null) {
            if (other.startsAt != null)
                return false;
        } else if (!startsAt.equals(other.startsAt))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EventOcurrence [startsAt=" + startsAt + ", endAt=" + endAt + ", isAllDay=" + isAllDay + "]";
    }

}
