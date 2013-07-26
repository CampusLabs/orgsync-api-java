package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.ApiResponse;
import com.orgsync.api.ClassificationsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.classifications.Classification;
import com.typesafe.config.Config;

public class ClassificationsIntegrationTest extends BaseIntegrationTest<ClassificationsResource> {

    private static final List<? extends Config> classifcationConfigs = DbTemplate.getList("classifications");
    private static final Config classificationConfig = classifcationConfigs.get(0);

    public ClassificationsIntegrationTest() {
        super(Resources.CLASSIFICATIONS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.CLASSIFICATIONS);
    }

    @Test
    public void testGetClassifications() throws Exception {
        List<Classification> results = getResult(getResource().getClassifications());
        testContainsIds(results, classifcationConfigs);
    }

    @Test
    public void testClassificationCRUD() throws Exception {
        String name = "new classification test";
        Classification created = getResult(getResource().createClassification(name));

        assertEquals(name, created.getName());

        Classification retrieved = getResult(getResource().getClassification(created.getId()));

        assertEquals(retrieved, created);

        String newName = "updated name";
        Classification updated = getResult(getResource().updateClassification(created.getId(), newName));

        assertEquals(newName, updated.getName());

        Success success = getResult(getResource().deleteClassification(updated.getId()));

        assertTrue(success.isSuccess());

        ApiResponse<Classification> response = getResource().getClassification(updated.getId()).get();

        assertFalse(response.isSuccess());
    }

    @Test
    public void testAddAndRemoveAccounts() throws Exception {
        List<? extends Config> users = DbTemplate.getList("users");
        Config user1 = users.get(0);
        Config user2 = users.get(1);

        int classificationId = classificationConfig.getInt("id");

        List<Account> accounts = getResult(getResource().listAccounts(classificationId));

        assertEquals(1, accounts.size());
        assertEquals(user1.getInt("id"), accounts.get(0).getId());

        List<Integer> user2List = Collections.singletonList(user2.getInt("id"));
        Success result = getResult(getResource().addAccountsToClassification(classificationId, user2List));

        assertTrue(result.isSuccess());

        accounts = getResult(getResource().listAccounts(classificationId));

        List<Integer> accountIds = getIdsForObjects(accounts);

        assertThat(accountIds, IsCollectionContaining.hasItems(user1.getInt("id"), user2.getInt("id")));

        result = getResult(getResource().removeAccountsFromClassification(classificationId, user2List));

        assertTrue(result.isSuccess());

        accounts = getResult(getResource().listAccounts(classificationId));

        assertEquals(1, accounts.size());
        assertEquals(user1.getInt("id"), accounts.get(0).getId());
    }
}
