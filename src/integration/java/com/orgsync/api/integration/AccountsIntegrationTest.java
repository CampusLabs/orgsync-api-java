package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.orgsync.api.AccountsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountDetail;
import com.orgsync.api.model.accounts.AccountFull;
import com.typesafe.config.Config;

public class AccountsIntegrationTest extends BaseIntegrationTest<AccountsResource> {

    private static final List<? extends Config> configAccounts = DbTemplate.getList("users");

    public AccountsIntegrationTest() {
        super(Resources.ACCOUNTS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.ACCOUNTS);
    }

    @Test
    public void testGetAccounts() throws InterruptedException, ExecutionException {
        List<Account> accounts = getResult(getResource().getAccounts());
        Set<Integer> actualIds = new HashSet<Integer>();

        for (Account account : accounts) {
            actualIds.add(account.getId());
        }

        Set<Integer> expectedIds = new HashSet<Integer>();

        for (Config account : configAccounts) {
            expectedIds.add(account.getInt("id"));
        }

        assertEquals(expectedIds, actualIds);
    }

    @Test
    public void testGetAccount() throws Exception {
        Config account = configAccounts.get(0);
        AccountFull result = getResult(getResource().getAccount(account.getInt("id")));

        assertEquals(account.getString("username"), result.getUsername());
    }

    @Test
    public void testGetAccountByEmail() throws Exception {
        Config account = configAccounts.get(0);
        AccountDetail result = getResult(getResource().getAccountByEmail(account.getString("email_address")));

        assertEquals(account.getInt("id"), result.getId());
    }

    @Test
    public void testGetAccountByUsername() throws Exception {
        Config account = configAccounts.get(0);
        AccountDetail result = getResult(getResource().getAccountByUsername(account.getString("username")));

        assertEquals(account.getInt("id"), result.getId());
    }

    @Test
    @Ignore("TODO")
    public void testGetAccountByCustomProfile() throws Exception {
        // TODO
    }

    @Test
    @Ignore("TODO")
    public void testGetCustomProfileFields() throws Exception {
        // TODO
    }

    @Test
    @Ignore("TODO")
    public void testUpdateAccount() throws Exception {
        // TODO
    }

}
