package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.AccountsResource;
import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountDetail;
import com.typesafe.config.Config;

public class AccountsIntegrationTest {

    private static final String testKey = DbTemplate.getString("api_key");
    private static final String host = "http://localhost:8080/api/v2";
    private static final ApiClient client = OrgSync.newApiClient(testKey, host);
    private static final AccountsResource resource = client.getResource(Resources.ACCOUNTS);

    @Test
    public void test() throws InterruptedException, ExecutionException {
        ApiResponse<List<Account>> apiResponse = resource.getAccounts().get();
        apiResponse.getResult();
    }

    @Test
    public void testGetAccountByEmail() throws Exception {
        Config account = DbTemplate.getList("users").get(0);
        ApiResponse<AccountDetail> response = resource.getAccountByEmail(account.getString("email_address")).get();
        AccountDetail result = response.getResult();

        assertEquals(account.getString("username"), result.getUsername());
    }

    @AfterClass
    public static void after() {
        client.destroy();
    }

}
