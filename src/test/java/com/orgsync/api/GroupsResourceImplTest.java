package com.orgsync.api;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;

public class GroupsResourceImplTest extends BaseResourceTest {

    private final GroupsResourceImpl groups = new GroupsResourceImpl(client);

    @Test
    public void testGetOrgGroups() throws Exception {
        groups.getOrgGroups(234);

        verifyRequest(RequestParams.get("/orgs/234/groups"));
    }

    @Test
    public void testGetGroup() throws Exception {
        groups.getGroup(345);

        verifyRequest(RequestParams.get("/groups/345"));
    }

    @Test
    public void testCreateGroup() throws Exception {
        String name = "testgroup";
        groups.createGroup(name, 456);

        FluentStringsMap params = new FluentStringsMap().add("name", name).add("org_id", "456");

        verifyRequest(RequestParams.post("/groups", params));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateGroupNullName() throws Exception {
        groups.createGroup(null, 123);
    }

    @Test
    public void testDeleteGroup() throws Exception {
        groups.deleteGroup(567);

        verifyRequest(RequestParams.delete("/groups/567"));
    }

    @Test
    public void testUpdateGroupInformation() throws Exception {
        String name = "newName";
        groups.updateGroup(678, name);

        FluentStringsMap params = new FluentStringsMap().add("name", name);

        verifyRequest(RequestParams.put("/groups/678", params));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateGroupInformationNoName() throws Exception {
        groups.updateGroup(234, null);
    }

    @Test
    public void testListGroupAccounts() throws Exception {
        groups.listAccounts(789);

        verifyRequest(RequestParams.get("/groups/789/accounts"));
    }

    @Test
    public void testAddAccountsToGroup() throws Exception {
        List<Integer> accountIds = Arrays.asList(11, 12, 13);
        groups.addAccountsToGroup(890, accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", "11,12,13");

        verifyRequest(RequestParams.post("/groups/890/accounts/add", params));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAccountsToGroupNullIds() throws Exception {
        groups.addAccountsToGroup(234, null);
    }

    @Test
    public void testRemoveAccountsFromGroup() throws Exception {
        List<Integer> accountIds = Arrays.asList(24, 25, 26);
        groups.removeAccountsToGroup(901, accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", "24,25,26");

        verifyRequest(RequestParams.post("/groups/901/accounts/remove", params));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAccountsFromGroupNullIds() throws Exception {
        groups.removeAccountsToGroup(234, null);

    }
}
