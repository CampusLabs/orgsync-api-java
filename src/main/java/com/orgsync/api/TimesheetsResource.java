package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.timesheets.Timesheet;

public interface TimesheetsResource {

    public ListenableFuture<ApiResponse<List<Timesheet>>> getTimesheets();

    public ListenableFuture<ApiResponse<List<Timesheet>>> getAccountTimesheets(int accountId);

    public ListenableFuture<ApiResponse<List<Timesheet>>> getOrgTimesheets(int orgId);

    public ListenableFuture<ApiResponse<List<Timesheet>>> getEventTimesheets(int eventId, String occurrenceDate);

}
