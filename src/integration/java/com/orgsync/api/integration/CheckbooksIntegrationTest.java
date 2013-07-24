package com.orgsync.api.integration;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.orgsync.api.CheckbooksResource;
import com.orgsync.api.Resources;
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
    @Ignore("TODO")
    public void testCreateCheckbook() throws Exception {

    }

    @Test
    @Ignore("TODO")
    public void testUpdateCheckbook() throws Exception {

    }

    @Test
    @Ignore("TODO")
    public void testDeleteCheckbook() throws Exception {

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
    @Ignore("TODO")
    public void testCreateCheckbookEntry() throws Exception {

    }

    @Test
    @Ignore("TODO")
    public void testUpdateCheckbookEntry() throws Exception {

    }

    @Test
    @Ignore("TODO")
    public void testDeleteCheckbookEntry() throws Exception {

    }
}
