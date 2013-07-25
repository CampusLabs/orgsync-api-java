package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.GroupsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.groups.Group;
import com.typesafe.config.Config;

public class GroupsIntegrationTest extends BaseIntegrationTest<GroupsResource> {

    private static final Config orgConfig = DbTemplate.getList("portals").get(0);
    private static final int orgId = orgConfig.getInt("id");
    private static final String defaultGroup = orgConfig.getString("default_join_group");

    public GroupsIntegrationTest() {
        super(Resources.GROUPS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.GROUPS);
    }

    @Test
    public void testGetOrgGroups() throws Exception {
        List<Group> result = getResult(getResource().getOrgGroups(orgId));

        Group foundGroup = null;
        for (Group group : result) {
            if (group.getName().equals(defaultGroup)) {
                foundGroup = group;
            }
        }

        assertNotNull(foundGroup);
        assertEquals(defaultGroup, foundGroup.getName());

        List<Integer> configIds = getIdsForConfigs(DbTemplate.getList("users"));

        assertThat(foundGroup.getAccountIds(),
                IsCollectionContaining.hasItems(configIds.toArray(new Integer[configIds.size()])));
    }

    @Test
    public void testCRUDGroup() throws Exception {
        String newGroupName = "New Group";
        Group created = getResult(getResource().createGroup(newGroupName, orgId));

        assertEquals(newGroupName, created.getName());
        assertEquals(Collections.emptyList(), created.getAccountIds());

        Group found = getResult(getResource().getGroup(created.getId()));

        assertEquals(created, found);

        String updatedName = "updated";
        Group updated = getResult(getResource().updateGroup(created.getId(), updatedName));

        assertEquals(updatedName, updated.getName());

        Success success = getResult(getResource().deleteGroup(found.getId()));

        assertTrue(success.isSuccess());
    }

    @Test
    public void testAddAndRemoveAccounts() throws Exception {
        String newGroupName = "Add To Group";
        Config user = DbTemplate.getList("users").get(0);

        Group created = getResult(getResource().createGroup(newGroupName, orgId));
        assertEquals(Collections.emptyList(), created.getAccountIds());

        List<Integer> userIdList = Collections.singletonList(user.getInt("id"));

        Success success = getResult(getResource().addAccountsToGroup(created.getId(), userIdList));
        assertTrue(success.isSuccess());

        List<Account> accounts = getResult(getResource().listAccounts(created.getId()));
        assertEquals(1, accounts.size());
        assertEquals(user.getInt("id"), accounts.get(0).getId());

        success = getResult(getResource().removeAccountsToGroup(created.getId(), userIdList));
        assertTrue(success.isSuccess());

        Group updated = getResult(getResource().getGroup(created.getId()));
        assertEquals(Collections.emptyList(), updated.getAccountIds());

        success = getResult(getResource().deleteGroup(created.getId()));
        assertTrue(success.isSuccess());
    }
}
