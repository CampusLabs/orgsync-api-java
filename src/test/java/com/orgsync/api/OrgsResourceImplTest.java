package com.orgsync.api;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.orgs.OrgUpdateRequest;

public class OrgsResourceImplTest extends BaseResourceTest {

    private final OrgsResourceImpl resource = new OrgsResourceImpl(client);

    @Test
    public void testGetOrgs() {
        resource.getOrgs();
        verifyRequest(RequestParams.get("/orgs"));
    }

    @Test
    public void testGetOrg() throws Exception {
        resource.getOrg(3434);

        verifyRequest(RequestParams.get("/orgs/3434"));
    }

    @Test
    public void testUpdateOrganization() throws Exception {
        String alternateId = "alternate";
        String shortName = "short name";
        OrgUpdateRequest request = new OrgUpdateRequest().setAlternateId(alternateId).setShortName(shortName);

        resource.updateOrg(1212, request);

        FluentStringsMap params = new FluentStringsMap().add("alternate_id", alternateId).add("short_name", shortName);

        verifyRequest(RequestParams.put("/orgs/1212", params));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateOrgWithNull() throws Exception {
        resource.updateOrg(12, null);
    }

    @Test
    public void testListAccounts() throws Exception {
        resource.listAccounts(123);
        verifyRequest(RequestParams.get("/orgs/123/accounts"));
    }

    @Test
    public void testAddAccounts() throws Exception {
        List<Integer> accountIds = Arrays.asList(1, 2, 3);
        resource.addAccounts(2323, accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", "1,2,3");
        verifyRequest(RequestParams.post("/orgs/2323/accounts/add", params));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAccountsWithNull() throws Exception {
        resource.addAccounts(12, null);
    }

    @Test
    public void testRemoveAccounts() throws Exception {
        List<Integer> accountIds = Arrays.asList(11, 12, 13);
        resource.removeAccounts(3434, accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", "11,12,13");
        verifyRequest(RequestParams.post("/orgs/3434/accounts/remove", params));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAccountsWithNull() throws Exception {
        resource.removeAccounts(12, null);
    }

}
