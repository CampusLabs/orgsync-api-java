package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.timesheets.Timesheet;

/*package*/class TimesheetsResourceImpl extends BaseResource implements TimesheetsResource {

    /* package */TimesheetsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Timesheet>>> getTimesheets() {
        return getResponse(RequestParams.get("/timesheets"), new TypeToken<List<Timesheet>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<Timesheet>>> getAccountTimesheets(final int accountId) {
        String endpoint = String.format("/accounts/%d/timesheets", accountId);
        return getResponse(RequestParams.get(endpoint), new TypeToken<List<Timesheet>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<Timesheet>>> getOrgTimesheets(final int orgId) {
        String endpoint = String.format("/orgs/%d/timesheets", orgId);
        return getResponse(RequestParams.get(endpoint), new TypeToken<List<Timesheet>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<Timesheet>>> getEventTimesheets(final int eventId,
            final String occurrenceDate) {
        checkNotNull(occurrenceDate);

        String endpoint = String.format("/events/%d/timesheets", eventId);
        FluentStringsMap params = new FluentStringsMap().add("occurrence", occurrenceDate);

        return getResponse(RequestParams.get(endpoint, params), new TypeToken<List<Timesheet>>() {
        }.getType());
    }

}
