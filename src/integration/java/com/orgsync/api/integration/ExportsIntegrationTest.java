/*
 * Copyright 2014 OrgSync
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

import com.orgsync.api.ExportsResource;
import com.orgsync.api.Resource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.AccountFull;
import com.orgsync.api.model.orgs.OrgFull;
import com.orgsync.api.model.timesheets.Timesheet;
import com.typesafe.config.Config;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

/**
 * @author steffyj
 */
public class ExportsIntegrationTest extends BaseIntegrationTest<ExportsResource> {

    public ExportsIntegrationTest() {
        super(Resources.EXPORTS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.EXPORTS);
    }

    @Test
    public void testGetAccounts() throws Exception {
        List<? extends Config> configAccounts = DbTemplate.getList("users");
        List<AccountFull> accounts = getResult(getResource().getAccounts());
        testContainsIds(accounts, configAccounts);
    }

    @Test
    public void testGetOrgs() throws Exception {
        List<? extends Config> configPortals = DbTemplate.getList("portals");
        List<OrgFull> orgs = getResult(getResource().getOrgs());
        testContainsIds(orgs, configPortals);
    }

    @Test
    public void testGetTimesheets() throws Exception {
        List<? extends Config> configTimesheets = DbTemplate.getList("timesheets");
        List<Timesheet> timesheets = getResult(getResource().getTimesheets());
        testContainsIds(timesheets, configTimesheets);
    }
}
