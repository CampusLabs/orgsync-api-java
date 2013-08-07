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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.events.EventQueryParams;

public class EventsResourceImplTest extends BaseResourceTest {

    private final EventsResourceImpl events = new EventsResourceImpl(client);

    @Test
    public void testGetEvents() throws Exception {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.set(2013, 0, 1, 10, 25, 35); // 0th month is Jan...

        Date startTime = cal.getTime();
        String keyword = "test";

        EventQueryParams params = new EventQueryParams().setStartDate(startTime).setKeyword(keyword);

        events.getEvents(params);

        FluentStringsMap queryParams = new FluentStringsMap()
                .add("keyword", keyword)
                .add("start_date", "2013-01-01T10:25:35Z");

        verifyRequest(RequestParams.get("/events", queryParams));
    }

    @Test
    public void testGetOrgEvents() throws Exception {
        events.getOrgEvents(321, new EventQueryParams());

        verifyRequest(RequestParams.get("/orgs/321/events"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetEventsThrowsNPEForNoQuery() throws Exception {
        events.getEvents(null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetOrgEventsThrowsNPEForNoQuery() throws Exception {
        events.getOrgEvents(123, null);
    }

}
