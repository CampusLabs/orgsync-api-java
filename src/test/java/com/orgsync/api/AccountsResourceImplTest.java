package com.orgsync.api;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.accounts.AccountUpdateRequest.ElementPair;

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
    public void testListCustomProfileFields() throws Exception {
        accounts.getCustomProfileFields();

        verifyRequest(RequestParams.get("/accounts/profile_fields"));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        String firstName = "Test";
        String email = "test@orgsync.com";
        ElementPair element = new ElementPair(37, "100");
        AccountUpdateRequest request = new AccountUpdateRequest().setFirstName(firstName).setEmail(email)
                .setElement(element);
        accounts.updateAccount(3311, request);

        FluentStringsMap params = new FluentStringsMap().add("first_name", firstName).add("email", email)
                .add("profile_entries[37]", "100");

        verifyRequest(RequestParams.put("/accounts/3311", params));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateAccountWithNull() throws Exception {
        accounts.updateAccount(123, null);
    }

}
