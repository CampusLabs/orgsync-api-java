package com.orgsync.api;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.orgs.AddAccounts;

public class OrgsResourceImplTest extends BaseResourceTest {

    private final OrgsResourceImpl resource = new OrgsResourceImpl(client);

    @Test
    public void testGetOrgs() {
        resource.getOrgs();
        verifyRequest(RequestParams.get("/orgs"));
    }

    @Test
    public void testAddAccounts() throws Exception {
        int orgId = 1;
        List<Integer> accountIds = Arrays.asList(1, 2, 3);
        AddAccounts accounts = new AddAccounts(orgId, accountIds);
        resource.addAccounts(accounts);

        String endpoint = String.format("/orgs/%d/accounts/add", orgId);
        FluentStringsMap params = new FluentStringsMap().add("ids", "1,2,3");
        verifyRequest(RequestParams.post(endpoint, params));
    }

    @Test
    public void testListAccounts() throws Exception {
        resource.listAccounts(123);
        verifyRequest(RequestParams.get("/orgs/123/accounts"));
    }
}
