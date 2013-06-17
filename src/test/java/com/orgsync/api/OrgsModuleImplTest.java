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
import com.orgsync.api.messages.orgs.AddAccounts;

public class OrgsModuleImplTest {

    private final ApiClientImpl client = mock(ApiClientImpl.class);
    private final OrgsModuleImpl module = new OrgsModuleImpl(client);

    @Test
    public void testGetOrgs() {
        module.getOrgs();
        verify(client).getResponse(eq(RequestParams.get("/orgs")),
                any(Type.class));
    }

    @Test
    public void testAddAccounts() throws Exception {
        int orgId = 1;
        List<Integer> accountIds = Arrays.asList(1, 2, 3);
        AddAccounts message = new AddAccounts(orgId, accountIds);
        module.addAccounts(message);

        String endpoint = String.format("/orgs/%d/accounts/add", orgId);
        FluentStringsMap params = new FluentStringsMap().add("ids", "1,2,3");
        verify(client).getResponse(eq(RequestParams.post(endpoint, params)),
                any(Type.class));
    }

    @Test
    public void testListAccounts() throws Exception {
        module.listAccounts(123);
        verify(client).getResponse(eq(RequestParams.get("/orgs/123/accounts")),
                any(Type.class));
    }
}
