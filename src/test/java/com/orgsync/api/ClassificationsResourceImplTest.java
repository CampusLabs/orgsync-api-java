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

import static com.orgsync.api.RequestParams.delete;
import static com.orgsync.api.RequestParams.get;
import static com.orgsync.api.RequestParams.post;
import static com.orgsync.api.RequestParams.put;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;

public class ClassificationsResourceImplTest extends BaseResourceTest {

    private final ClassificationsResourceImpl classifications = new ClassificationsResourceImpl(client);

    @Test
    public void testGetClassifications() throws Exception {
        classifications.getClassifications();
        verifyRequest(get("/classifications"));
    }

    @Test
    public void testGetClassification() throws Exception {
        classifications.getClassification(123);
        verifyRequest(get("/classifications/123"));
    }

    @Test
    public void testDeleteClassification() throws Exception {
        classifications.deleteClassification(234);
        verifyRequest(delete("/classifications/234"));
    }

    @Test
    public void testCreateClassification() throws Exception {
        String name = "test";
        classifications.createClassification(name);
        verifyRequest(post("/classifications", new FluentStringsMap().add("name", name)));
    }

    @Test
    public void testUpdateClassification() throws Exception {
        String name = "updated";
        classifications.updateClassification(345, name);
        verifyRequest(put("/classifications/345", new FluentStringsMap().add("name", name)));
    }

    @Test
    public void testListAccounts() throws Exception {
        classifications.listAccounts(567);
        verifyRequest(get("/classifications/567/accounts"));
    }

    @Test
    public void testAddAccountsToClassification() throws Exception {
        List<Integer> accountIds = Arrays.asList(11, 12, 13);
        classifications.addAccountsToClassification(890, accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", "11,12,13");

        verifyRequest(post("/classifications/890/accounts/add", params));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAccountsToClassificationsNullIds() throws Exception {
        classifications.addAccountsToClassification(234, null);
    }

    @Test
    public void testRemoveAccountsFromClassification() throws Exception {
        List<Integer> accountIds = Arrays.asList(24, 25, 26);
        classifications.removeAccountsFromClassification(901, accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", "24,25,26");

        verifyRequest(RequestParams.post("/classifications/901/accounts/remove", params));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAccountsFromClassificationNullIds() throws Exception {
        classifications.removeAccountsFromClassification(234, null);
    }

}
