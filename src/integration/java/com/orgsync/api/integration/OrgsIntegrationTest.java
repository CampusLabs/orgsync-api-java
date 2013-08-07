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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.OrgsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgUpdateRequest;
import com.typesafe.config.Config;

public class OrgsIntegrationTest extends BaseIntegrationTest<OrgsResource> {

    private static final List<? extends Config> configPortals = DbTemplate.getList("portals");
    private static final List<? extends Config> configUmbrellas = DbTemplate.getList("umbrellas");

    private static final Config testPortalConfig = configPortals.get(1);

    public OrgsIntegrationTest() {
        super(Resources.ORGS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.ORGS);
    }

    @Test
    public void testGetOrgs() throws Exception {
        List<Org> orgs = getResult(getResource().getOrgs());

        List<Config> allConfigPortals = new ArrayList<Config>(configPortals);
        allConfigPortals.addAll(configUmbrellas);

        testContainsIds(orgs, allConfigPortals);
    }

    @Test
    public void testGetOrg() throws Exception {
        Config config = configPortals.get(0);
        Org org = getResult(getResource().getOrg(config.getInt("id")));

        assertEquals(config.getString("short_name"), org.getShortName());
    }

    @Test
    public void testUpdateOrg() throws Exception {
        int testPortalId = testPortalConfig.getInt("id");
        String updatedShortName = "update";

        Org original = getResult(getResource().getOrg(testPortalId));

        assertEquals(testPortalConfig.getString("short_name"), original.getShortName());

        Org updated = getResult(getResource().updateOrg(testPortalId,
                new OrgUpdateRequest().setShortName(updatedShortName)));

        assertEquals(updatedShortName, updated.getShortName());

        // put it back the way it was
        getResource().updateOrg(testPortalId,
                new OrgUpdateRequest().setShortName(testPortalConfig.getString("short_name")));

        Org reverted = getResult(getResource().getOrg(testPortalId));

        assertEquals(testPortalConfig.getString("short_name"), reverted.getShortName());
    }

    @Test
    public void testListAccounts() throws Exception {
        Config config = configPortals.get(0);
        List<Account> accounts = getResult(getResource().listAccounts(config.getInt("id")));

        Set<String> returnedUsers = new HashSet<String>();
        for (Account account : accounts) {
            returnedUsers.add(account.getUsername());
        }

        Set<String> expectedUsers = new HashSet<String>(config.getStringList("users"));
        assertEquals(expectedUsers, returnedUsers);
    }

    @Test
    public void testAddAndRemoveAccounts() throws Exception {
        int testPortalId = testPortalConfig.getInt("id");

        List<Account> accounts = getResult(getResource().listAccounts(testPortalId));

        assertTrue(accounts.isEmpty());

        Config user = DbTemplate.getList("users").get(0);
        int userId = user.getInt("id");

        Success result = getResult(getResource().addAccounts(testPortalId, Collections.singletonList(userId)));

        assertTrue(result.isSuccess());

        accounts = getResult(getResource().listAccounts(testPortalId));

        assertEquals(1, accounts.size());
        assertEquals(user.getString("username"), accounts.get(0).getUsername());

        result = getResult(getResource().removeAccounts(testPortalId, Collections.singletonList(userId)));

        assertTrue(result.isSuccess());

        accounts = getResult(getResource().listAccounts(testPortalId));

        assertTrue(accounts.isEmpty());

    }

}
