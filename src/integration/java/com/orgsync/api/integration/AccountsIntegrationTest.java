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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.orgsync.api.ApiResponse;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.AccountAttributes;
import com.orgsync.api.model.accounts.AccountCreateRequest;
import com.orgsync.api.model.forms.FormUpdate;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.AccountsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountDetail;
import com.orgsync.api.model.accounts.AccountFull;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.accounts.CustomProfileField;
import com.typesafe.config.Config;

import static org.junit.Assert.*;

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
    public void testGetAccountsByCustomProfileResponse() throws Exception {
        String profileField = accountConfig.getConfigList("profile").get(0).getString("data");
        List<AccountDetail> result = getResult(getResource().getAccountsByCustomProfileResponse(1, profileField));

        testContainsIds(result, Arrays.asList(accountConfig));
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
    public void testCreateDeleteAccount() throws Exception {
        String username = "create" + DbTemplate.getString("sso_username_suffix");
        String first = "Created";
        String last = "User";
        String email = "create@orgsync.com";

        ApiResponse<AccountDetail> response = getResource().getAccountByUsername(username).get();
        assertFalse("Should not have found user with username " + username, response.isSuccess());
        assertEquals("Record not found", response.getError().getMessage());

        AccountAttributes attrs = new AccountAttributes().setFirstName(first).setLastName(last).setEmailAddress(email);
        AccountCreateRequest request = new AccountCreateRequest().setUsername(username).setAccountAttributes(attrs);

        AccountFull result = getResult(getResource().createAccount(request));

        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals(first, result.getFirstName());
        assertEquals(last, result.getLastName());

        Success success = getResult(getResource().deleteAccount(result.getId()));

        assertTrue("Delete was not a success!", success.isSuccess());

        response = getResource().getAccountByUsername(username).get();
        assertFalse("Should not have found user with username " + username, response.isSuccess());
        assertEquals("Record not found", response.getError().getMessage());
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
                        .addProfileUpdate(new FormUpdate(profileConfig.getInt("id"), profileUpdate))));

        assertEquals(updatedLastName, result.getLastName());
        assertEquals(profileUpdate, result.getProfileResponses().get(0).getData());

        AccountFull reverted = getResult(getResource().updateAccount(accountId,
                new AccountUpdateRequest()
                        .setLastName(accountConfig.getString("last_name"))
                        .addProfileUpdate(new FormUpdate(profileConfig.getInt("id"), accountProfile.getString("data")))));

        assertEquals(accountConfig.getString("last_name"), reverted.getLastName());
        assertEquals(accountProfile.getString("data"), reverted.getProfileResponses().get(0).getData());
    }
}
