/*
 * Copyright 2013 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
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
