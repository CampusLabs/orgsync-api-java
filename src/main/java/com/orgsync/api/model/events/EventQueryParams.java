package com.orgsync.api.model.events;

import java.util.Date;

public class EventQueryParams {

    private Date startDate;
    private Date endDate;
    private String keyword;

    public final Date getStartDate() {
        return startDate;
    }

    public final EventQueryParams setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public final Date getEndDate() {
        return endDate;
    }

    public final EventQueryParams setEndDate(final Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public final String getKeyword() {
        return keyword;
    }

    public final EventQueryParams setKeyword(final String keyword) {
        this.keyword = keyword;
        return this;
    }

}
