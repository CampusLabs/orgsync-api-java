package com.orgsync.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.orgs.AddAccounts;

public class OrgsResourceImplTest {

    private final ApiClientImpl client = mock(ApiClientImpl.class);
    private final OrgsResourceImpl resource = new OrgsResourceImpl(client);

    @Test
    public void testGetOrgs() {
        resource.getOrgs();
        verify(client).getResponse(eq(RequestParams.get("/orgs")),
                any(Type.class));
    }

    @Test
    public void testAddAccounts() throws Exception {
        int orgId = 1;
        List<Integer> accountIds = Arrays.asList(1, 2, 3);
        AddAccounts accounts = new AddAccounts(orgId, accountIds);
        resource.addAccounts(accounts);

        String endpoint = String.format("/orgs/%d/accounts/add", orgId);
        FluentStringsMap params = new FluentStringsMap().add("ids", "1,2,3");
        verify(client).getResponse(eq(RequestParams.post(endpoint, params)),
                any(Type.class));
    }

    @Test
    public void testListAccounts() throws Exception {
        resource.listAccounts(123);
        verify(client).getResponse(eq(RequestParams.get("/orgs/123/accounts")),
                any(Type.class));
    }
}
