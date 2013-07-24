package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.orgsync.api.AccountsResource;
import com.orgsync.api.ApiResponse;
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

    @Test
    public void test() throws InterruptedException, ExecutionException {
        ApiResponse<List<Account>> apiResponse = getResource().getAccounts().get();
        List<Account> accounts = apiResponse.getResult();
        List<Integer> actualIds = new ArrayList<Integer>();

        for (Account account : accounts) {
            actualIds.add(account.getId());
        }

        List<Integer> expectedIds = new ArrayList<Integer>();

        for (Config account : configAccounts) {
            expectedIds.add(account.getInt("id"));
        }

        assertEquals(expectedIds, actualIds);
    }

    @Test
    public void testGetAccount() throws Exception {
        Config account = configAccounts.get(0);
        AccountFull result = getResource().getAccount(account.getInt("id")).get().getResult();

        assertEquals(account.getString("username"), result.getUsername());
    }

    @Test
    public void testGetAccountByEmail() throws Exception {
        Config account = configAccounts.get(0);
        ApiResponse<AccountDetail> response = getResource().getAccountByEmail(account.getString("email_address")).get();
        AccountDetail result = response.getResult();

        assertEquals(account.getInt("id"), result.getId());
    }

    @Test
    public void testGetAccountByUsername() throws Exception {
        Config account = configAccounts.get(0);
        ApiResponse<AccountDetail> response = getResource().getAccountByUsername(account.getString("username")).get();
        AccountDetail result = response.getResult();

        assertEquals(account.getInt("id"), result.getId());
    }

    @Test
    public void testGetAccountByCustomProfile() throws Exception {
        // TODO
    }

    @Test
    public void testGetCustomProfileFields() throws Exception {
        // TODO
    }

    @Test
    public void testUpdateAccount() throws Exception {
        // TODO
    }

}
