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
package com.orgsync.api.integration;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.EventsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.events.Event;
import com.orgsync.api.model.events.EventQueryParams;
import com.typesafe.config.Config;

public class EventsIntegrationTest extends BaseIntegrationTest<EventsResource> {

    private static final List<? extends Config> eventsConfig = DbTemplate.getList("events");

    public EventsIntegrationTest() {
        super(Resources.EVENTS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.EVENTS);
    }

    @Test
    public void testGetEvents() throws Exception {
        List<Event> result = getResult(getResource().getEvents(new EventQueryParams()));

        testContainsIds(result, eventsConfig);
    }

    @Test
    public void testGetEventsWithQuery() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(1999, 1, 1);
        Date endDate = cal.getTime();
        List<Event> result = getResult(getResource().getEvents(new EventQueryParams().setEndDate(endDate)));

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetOrgEvents() throws Exception {
        Config portalConfig = DbTemplate.getList("portals").get(1);

        List<Event> result = getResult(getResource().getOrgEvents(portalConfig.getInt("id"), new EventQueryParams()));

        List<Integer> expectedIds = new ArrayList<Integer>();
        for (Config config : eventsConfig) {
            if (config.getString("portal_short_name").equals(portalConfig.getString("short_name"))) {
                expectedIds.add(config.getInt("id"));
            }
        }

        List<Integer> ids = getIdsForObjects(result);

        assertThat(ids, IsCollectionContaining.hasItems(expectedIds.toArray(new Integer[expectedIds.size()])));
    }
}
