package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.AccountsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountDetail;
import com.orgsync.api.model.accounts.AccountFull;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.accounts.AccountUpdateRequest.ElementPair;
import com.orgsync.api.model.accounts.CustomProfileField;
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
    public void testGetAccountByCustomProfile() throws Exception {
        String profileField = accountConfig.getConfigList("profile").get(0).getString("data");
        AccountDetail result = getResult(getResource().getAccountByCustomProfile(profileField));

        assertEquals(accountConfig.getString("username"), result.getUsername());
    }

    @Test
    public void testGetCustomProfileFields() throws Exception {
        List<? extends Config> profileFieldsConfig = DbTemplate.getList("custom_profile");
        List<CustomProfileField> result = getResult(getResource().getCustomProfileFields());

        List<String> configNames = new ArrayList<String>();
        for (Config fieldConfig : profileFieldsConfig) {
            configNames.add(fieldConfig.getString("name"));
        }

        List<String> resultNames = new ArrayList<String>();
        for (CustomProfileField field : result) {
            resultNames.add(field.getName());
        }

        assertThat(resultNames, IsCollectionContaining.hasItems(configNames.toArray(new String[configNames.size()])));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Config accountConfig = configAccounts.get(1);
        int accountId = accountConfig.getInt("id");
        Config accountProfile = accountConfig.getConfigList("profile").get(0);

        Config profileConfig = DbTemplate.getList("custom_profile").get(0);

        String updatedLastName = "j";
        String profileUpdate = "updated";

        AccountFull original = getResult(getResource().getAccount(accountId));

        assertEquals(accountConfig.getString("last_name"), original.getLastName());
        assertEquals(accountProfile.getString("data"), original.getProfileResponses().get(0).getData());

        AccountFull result = getResult(getResource().updateAccount(accountId,
                new AccountUpdateRequest()
                        .setLastName(updatedLastName)
                        .setElement(new ElementPair(profileConfig.getInt("id"), profileUpdate))));

        assertEquals(updatedLastName, result.getLastName());
        assertEquals(profileUpdate, result.getProfileResponses().get(0).getData());

        AccountFull reverted = getResult(getResource().updateAccount(accountId,
                new AccountUpdateRequest()
                        .setLastName(accountConfig.getString("last_name"))
                        .setElement(new ElementPair(profileConfig.getInt("id"), accountProfile.getString("data")))));

        assertEquals(accountConfig.getString("last_name"), reverted.getLastName());
        assertEquals(accountProfile.getString("data"), reverted.getProfileResponses().get(0).getData());
    }
}
