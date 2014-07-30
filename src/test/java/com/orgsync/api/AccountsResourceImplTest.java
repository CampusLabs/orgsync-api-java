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
package com.orgsync.api;

import com.orgsync.api.model.accounts.AccountAttributes;
import com.orgsync.api.model.accounts.AccountCreateRequest;
import com.orgsync.api.model.forms.FormUpdate;
import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class AccountsResourceImplTest extends BaseResourceTest {

    private final AccountsResourceImpl accounts = new AccountsResourceImpl(client);

    @Test
    public void testGetAccounts() throws Exception {
        accounts.getAccounts();

        verifyRequest(RequestParams.get("/accounts"));
    }

    @Test
    public void testGetAccount() throws Exception {
        accounts.getAccount(2211);

        verifyRequest(RequestParams.get("/accounts/2211"));
    }

    @Test
    public void testGetAccountByEmail() throws Exception {
        String email = "test@orgsync.com";
        accounts.getAccountByEmail(email);

        verifyRequest(RequestParams.get("/accounts/email/" + email));
    }

    @Test(expected = NullPointerException.class)
    public void testGetAccountsByEmailWithNull() throws Exception {
        accounts.getAccountByEmail(null);
    }

    @Test
    public void testGetAccountsByUsername() throws Exception {
        String username = "testuser";
        accounts.getAccountByUsername(username);

        verifyRequest(RequestParams.get("/accounts/username/" + username));
    }

    @Test(expected = NullPointerException.class)
    public void testGetAccountsByUsernameWithNull() throws Exception {
        accounts.getAccountByUsername(null);
    }

    @Test
    public void testGetAccountByCustomProfile() throws Exception {
        String keyword = "test";
        accounts.getAccountByCustomProfile(keyword);

        verifyRequest(RequestParams.get("/accounts/custom_profile/" + keyword));
    }

    @Test(expected = NullPointerException.class)
    public void testGetAccountByCustomProfileWithNull() throws Exception {
        accounts.getAccountByCustomProfile(null);
    }

    @Test
    public void testGetAccountsByCustomProfileResponse() throws Exception {
        int questionId = 234;
        String responseQuery = "test*";
        accounts.getAccountsByCustomProfileResponse(questionId, responseQuery);

        verifyRequest(RequestParams.get(String.format("/accounts/custom_profile/%d/%s", questionId, responseQuery)));
    }

    @Test(expected = NullPointerException.class)
    public void testGetAccountsByCustomProfileResponseWithNull() throws Exception {
        accounts.getAccountsByCustomProfileResponse(1, null);
    }

    @Test
    public void testListCustomProfileFields() throws Exception {
        accounts.getCustomProfileFields();

        verifyRequest(RequestParams.get("/accounts/profile_fields"));
    }

    @Test
    public void testCreateAccount() throws Exception {
        String username = "test-user";
        String first = "First";
        String last = "Last";
        String email = "some.email@orgsync.com";

        AccountAttributes attrs = new AccountAttributes().setEmailAddress(email).setFirstName(first).setLastName(last);
        AccountCreateRequest request = new AccountCreateRequest().setUsername(username).setAccountAttributes(attrs);

        accounts.createAccount(request);

        ArgumentCaptor<RequestParams> argument = ArgumentCaptor.forClass(RequestParams.class);
        verify(client).getResponse(argument.capture(), any(Type.class));

        RequestParams params = argument.getValue();

        assertEquals("POST", params.getMethod());
        assertEquals("/accounts", params.getEndpoint());
        assertEquals(new FluentStringsMap(), params.getQueryParams());

        AccountCreateRequest json = JsonSerializer.fromJson(params.getBody(), AccountCreateRequest.class);
        assertEquals(request, json);
    }

    @Test
    public void testDeleteAccount() throws Exception {
        int accountId = 566;
        accounts.deleteAccount(accountId);
        verifyRequest(RequestParams.delete("/accounts/" + accountId));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        String firstName = "Test";
        String email = "test@orgsync.com";
        int profileField = 456;
        String profileUpdate = "a profile update";

        AccountUpdateRequest request = new AccountUpdateRequest()
                .setFirstName(firstName).setEmail(email)
                .addProfileUpdate(new FormUpdate(profileField, profileUpdate));
        accounts.updateAccount(3311, request);

        FluentStringsMap params = new FluentStringsMap()
                .add("first_name", firstName)
                .add("email", email)
                .add(String.format("profile_responses[%d]", profileField), profileUpdate);

        verifyRequest(RequestParams.put("/accounts/3311", params));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateAccountWithNull() throws Exception {
        accounts.updateAccount(123, null);
    }

}
