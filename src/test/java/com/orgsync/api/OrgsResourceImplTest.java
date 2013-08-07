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
