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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.MembershipLogsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.membership_logs.MembershipLogEntry;
import com.orgsync.api.model.membership_logs.MembershipLogEntryRequest;
import com.typesafe.config.Config;

public class MembershipLogsIntergrationTest extends BaseIntegrationTest<MembershipLogsResource> {

    public MembershipLogsIntergrationTest() {
        super(Resources.MEMBERSHIP_LOGS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.MEMBERSHIP_LOGS);
    }

    @Test
    public void testGetMembershipLogEntries() throws Exception {
        List<MembershipLogEntry> result = getResult(getResource().getLogEntries(new MembershipLogEntryRequest()));

        assertTrue(result.size() >= 2);
    }

    @Test
    public void testGetMembershipLogForAcount() throws Exception {
        Config userConfig = DbTemplate.getList("users").get(0);
        Config portalConfig = DbTemplate.getList("portals").get(0);

        List<MembershipLogEntry> result = getResult(getResource().getLogEntries(
                new MembershipLogEntryRequest().setAccountId(userConfig.getInt("id"))));

        MembershipLogEntry entry = result.get(0);
        assertEquals("Join", entry.getAction());
        assertEquals(userConfig.getInt("id"), entry.getAccountId());
        assertEquals(portalConfig.getInt("id"), entry.getOrgId());
    }

}
