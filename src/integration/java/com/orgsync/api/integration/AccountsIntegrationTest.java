package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
    private static final Config accountConfig = configAccounts.get(0);

    public AccountsIntegrationTest() {
        super(Resources.ACCOUNTS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.ACCOUNTS);
    }

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = getResult(getResource().getAccounts());
        testContainsIds(accounts, configAccounts);
    }

    @Test
    public void testGetAccount() throws Exception {
        AccountFull result = getResult(getResource().getAccount(accountConfig.getInt("id")));

        assertEquals(accountConfig.getString("username"), result.getUsername());
    }

    @Test
    public void testGetAccountByEmail() throws Exception {
        AccountDetail result = getResult(getResource().getAccountByEmail(accountConfig.getString("email_address")));

        assertEquals(accountConfig.getInt("id"), result.getId());
    }

    @Test
    public void testGetAccountByUsername() throws Exception {
        AccountDetail result = getResult(getResource().getAccountByUsername(accountConfig.getString("username")));

        assertEquals(accountConfig.getInt("id"), result.getId());
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
