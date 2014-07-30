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

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.ApiResponse;
import com.orgsync.api.CheckbooksResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.checkbooks.Checkbook;
import com.orgsync.api.model.checkbooks.CheckbookEntry;
import com.typesafe.config.Config;

public class CheckbooksIntegrationTest extends BaseIntegrationTest<CheckbooksResource> {

    private static final Config portalConfig = DbTemplate.getList("portals").get(0);
    private static final List<? extends Config> checkbooksConfig = portalConfig.getConfigList("checkbooks");
    private static final Config checkbookConfig = checkbooksConfig.get(0);

    public CheckbooksIntegrationTest() {
        super(Resources.CHECKBOOKS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.CHECKBOOKS);
    }

    @Test
    public void testGetCheckbooks() throws Exception {
        List<Checkbook> checkbooks = getResult(getResource().getCheckbooks());

        testContainsIds(checkbooks, checkbooksConfig);
    }

    @Test
    public void testGetCheckbook() throws Exception {
        Checkbook checkbook = getResult(getResource().getCheckbook(checkbookConfig.getInt("id")));

        assertEquals(checkbookConfig.getString("name"), checkbook.getName());
    }

    @Test
    public void testCUDCheckbook() throws Exception {
        int portalId = portalConfig.getInt("id");
        String checkbookName = "test create";
        boolean isReadOnly = false;
        Checkbook result = getResult(getResource().createCheckbook(portalId, checkbookName, isReadOnly));

        assertEquals(checkbookName, result.getName());
        assertEquals(portalId, result.getOrg().getId());
        assertEquals(isReadOnly, result.isReadOnly());
        assertEquals("0.0", result.getBalance());

        String updatedName = "updated";
        boolean updateReadOnly = true;
        Checkbook updated = getResult(getResource().updateCheckbook(result.getId(), updatedName, updateReadOnly));

        assertEquals(updatedName, updated.getName());
        assertEquals(updateReadOnly, updated.isReadOnly());

        Success success = getResult(getResource().deleteCheckbook(result.getId()));

        assertTrue(success.isSuccess());

        ApiResponse<Checkbook> checkbook = getResource().getCheckbook(result.getId()).get();

        assertFalse(checkbook.isSuccess());
    }

    @Test
    public void testGetOrgCheckbooks() throws Exception {
        List<Checkbook> checkbooks = getResult(getResource().getOrgCheckbooks(portalConfig.getInt("id")));

        Set<Integer> actualIds = new HashSet<Integer>();

        for (Checkbook checkbook : checkbooks) {
            actualIds.add(checkbook.getId());
        }

        Set<Integer> expectedIds = new HashSet<Integer>();

        for (Config checkbook : checkbooksConfig) {
            expectedIds.add(checkbook.getInt("id"));
        }

        assertThat(actualIds, hasItems(expectedIds.toArray(new Integer[expectedIds.size()])));
    }

    @Test
    public void testGetCheckbookEntries() throws Exception {
        List<CheckbookEntry> entries = getResult(getResource().getCheckbookEntries(checkbookConfig.getInt("id")));

        testContainsIds(entries, checkbookConfig.getConfigList("entries"));
    }

    @Test
    public void testGetCheckbookEntry() throws Exception {
        Config entryConfig = checkbookConfig.getConfigList("entries").get(0);

        CheckbookEntry entry = getResult(getResource().getCheckbookEntry(entryConfig.getInt("id")));

        assertEquals(entryConfig.getString("description"), entry.getDescription());
    }

    @Test
    public void testCUDCheckbookEntry() throws Exception {
        int checkbookId = checkbookConfig.getInt("id");
        String amount = "9.99";
        String description = "An added checkbook entry";

        CheckbookEntry entry = getResult(getResource().createCheckbookEntry(checkbookId, amount, description));

        assertEquals(amount, entry.getAmount());
        assertEquals(description, entry.getDescription());
        assertEquals(checkbookId, entry.getCheckbook().getId());

        String updatedAmount = "19.99";
        String updatedDescription = "I updated this description";

        entry = getResult(getResource().updateCheckbookEntry(entry.getId(), updatedAmount, updatedDescription));

        assertEquals(updatedAmount, entry.getAmount());
        assertEquals(updatedDescription, entry.getDescription());

        Success success = getResult(getResource().deleteCheckbookEntry(entry.getId()));

        assertTrue(success.isSuccess());
    }

}
