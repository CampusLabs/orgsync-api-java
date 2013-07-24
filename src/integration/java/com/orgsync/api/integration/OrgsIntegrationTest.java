package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.orgsync.api.OrgsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.orgs.Org;
import com.typesafe.config.Config;

public class OrgsIntegrationTest extends BaseIntegrationTest<OrgsResource> {

    private static final List<? extends Config> configPortals = DbTemplate.getList("portals");
    private static final List<? extends Config> configUmbrellas = DbTemplate.getList("umbrellas");

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
    @Ignore("TODO")
    public void testUpdateOrg() throws Exception {

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
    @Ignore("TODO")
    public void testAddAccounts() throws Exception {

    }

    @Test
    @Ignore("TODO")
    public void testRemoveAccounts() throws Exception {

    }

}
