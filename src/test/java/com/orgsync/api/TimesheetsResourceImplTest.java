package com.orgsync.api;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;

public class TimesheetsResourceImplTest extends BaseResourceTest {

    private final TimesheetsResourceImpl timesheets = new TimesheetsResourceImpl(client);

    @Test
    public void testGetTimesheets() throws Exception {
        timesheets.getTimesheets();

        verifyRequest(RequestParams.get("/timesheets"));
    }

    @Test
    public void testGetAccountTimesheets() throws Exception {
        timesheets.getAccountTimesheets(654);

        verifyRequest(RequestParams.get("/accounts/654/timesheets"));
    }

    @Test
    public void testGetOrgTimesheets() throws Exception {
        timesheets.getOrgTimesheets(231);

        verifyRequest(RequestParams.get("/orgs/231/timesheets"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetEventTimesheetsWithoutOccurrence() throws Exception {
        timesheets.getEventTimesheets(123, null);
    }

    @Test
    public void testGetEventTimesheets() throws Exception {
        String occurrence = "2013-01-01";
        timesheets.getEventTimesheets(345, occurrence);

        verifyRequest(RequestParams.get("/events/345/timesheets",
                new FluentStringsMap().add("occurrence", occurrence)));
    }
}
