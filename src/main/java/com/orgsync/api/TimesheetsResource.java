package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.timesheets.Timesheet;

/**
 * <p>
 * Access to the timesheets resource from the OrgSync API. This allows a client to get timesheets for accounts, orgs, or
 * events.
 * 
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/timesheets">https://api.orgsync.com/api/docs/v2/timesheets</a>
 * 
 * 
 * @author steffyj
 * 
 */
public interface TimesheetsResource {

    /**
     * Get all of the timesheets for this community.
     * 
     * @return a future to a response with all timesheets
     */
    public ListenableFuture<ApiResponse<List<Timesheet>>>
            getTimesheets();

    /**
     * Get all of the timesheets for a given account.
     * 
     * @param accountId
     *            the account to get the timesheets for
     * @return a future to a response with the list of timesheets
     */
    public ListenableFuture<ApiResponse<List<Timesheet>>>
            getAccountTimesheets(int accountId);

    /**
     * Get all of the timesheets for a given org.
     * 
     * @param orgId
     *            the id of the org to get timesheets for
     * @return a future to a response with a list of timesheets
     */
    public ListenableFuture<ApiResponse<List<Timesheet>>>
            getOrgTimesheets(int orgId);

    /**
     * Get all of the timesheets for a particular instance of an event.
     * 
     * @param eventId
     *            the id of the event
     * @param occurrenceDate
     *            the date of the event in <code>YYYY-MM-DD</code> format
     * @return a future to a response with a list of timesheets
     */
    public ListenableFuture<ApiResponse<List<Timesheet>>>
            getEventTimesheets(int eventId, String occurrenceDate);

}
