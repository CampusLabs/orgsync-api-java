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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.Resources;
import com.orgsync.api.TimesheetsResource;
import com.orgsync.api.model.timesheets.Timesheet;
import com.typesafe.config.Config;

public class TimesheetsIntegrationTest extends BaseIntegrationTest<TimesheetsResource> {

    private static final List<? extends Config> timesheetsConfig = DbTemplate.getList("timesheets");

    public TimesheetsIntegrationTest() {
        super(Resources.TIMESHEETS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.TIMESHEETS);
    }

    @Test
    public void testGetTimesheets() throws Exception {
        List<Timesheet> result = getResult(getResource().getTimesheets());

        testContainsIds(result, timesheetsConfig);
    }

    @Test
    public void testGetAccountTimesheets() throws Exception {
        Config userConfig = DbTemplate.getList("users").get(0);
        int userId = userConfig.getInt("id");

        List<Timesheet> result = getResult(getResource().getAccountTimesheets(userId));

        List<Integer> expectedIds = new ArrayList<Integer>();
        for (Config config : timesheetsConfig) {
            if (config.getString("account_username").equals(userConfig.getString("username"))) {
                expectedIds.add(config.getInt("id"));
            }
        }

        List<Integer> ids = getIdsForObjects(result);

        assertThat(ids, IsCollectionContaining.hasItems(expectedIds.toArray(new Integer[expectedIds.size()])));
    }

    @Test
    public void testGetOrgTimesheets() throws Exception {
        Config portalConfig = DbTemplate.getList("portals").get(1);

        List<Timesheet> result = getResult(getResource().getOrgTimesheets(portalConfig.getInt("id")));

        List<Integer> expectedIds = new ArrayList<Integer>();
        for (Config config : timesheetsConfig) {
            if (config.getString("portal_short_name").equals(portalConfig.getString("short_name"))) {
                expectedIds.add(config.getInt("id"));
            }
        }

        List<Integer> ids = getIdsForObjects(result);

        assertThat(ids, IsCollectionContaining.hasItems(expectedIds.toArray(new Integer[expectedIds.size()])));
    }

    @Test
    public void testGetEventTimesheets() throws Exception {
        List<? extends Config> eventsConfig = DbTemplate.getList("events");
        Config eventConfig = eventsConfig.get(eventsConfig.size() - 1);

        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        List<Timesheet> result = getResult(getResource().getEventTimesheets(eventConfig.getInt("id"), dateString));

        List<Integer> expectedIds = new ArrayList<Integer>();
        for (Config config : timesheetsConfig) {
            if (config.hasPath("event_title") && config.getString("event_title").equals(eventConfig.getString("title"))) {
                expectedIds.add(config.getInt("id"));
            }
        }

        List<Integer> ids = getIdsForObjects(result);

        assertThat(ids, IsCollectionContaining.hasItems(expectedIds.toArray(new Integer[expectedIds.size()])));

    }
}
