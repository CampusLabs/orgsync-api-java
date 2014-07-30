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

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;

public class CheckbooksResourceImplTest extends BaseResourceTest {

    private final CheckbooksResourceImpl checkbooks = new CheckbooksResourceImpl(client);

    @Test
    public void testGetCheckbooks() throws Exception {
        checkbooks.getCheckbooks();

        verifyRequest(RequestParams.get("/checkbooks"));
    }

    @Test
    public void testGetCheckbook() throws Exception {
        checkbooks.getCheckbook(4455);

        verifyRequest(RequestParams.get("/checkbooks/4455"));
    }

    @Test
    public void testCreateCheckbook() throws Exception {
        String name = "testbook";
        boolean isReadOnly = true;
        checkbooks.createCheckbook(3345, name, isReadOnly);

        FluentStringsMap params = new FluentStringsMap().add("name", name).add("is_read_only", "true");

        verifyRequest(RequestParams.post("/orgs/3345/checkbooks", params));
    }

    @Test
    public void testUpdateCheckbook() throws Exception {
        String name = "updatebook";
        boolean isReadOnly = false;
        checkbooks.createCheckbook(3345, name, isReadOnly);

        FluentStringsMap params = new FluentStringsMap().add("name", name).add("is_read_only", "false");

        verifyRequest(RequestParams.post("/orgs/3345/checkbooks", params));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCheckbookWithNull() throws Exception {
        checkbooks.createCheckbook(123, null, true);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCheckbookWithNull() throws Exception {
        checkbooks.updateCheckbook(123, null, true);
    }

    @Test
    public void testDeleteCheckbook() throws Exception {
        checkbooks.deleteCheckbook(432);

        verifyRequest(RequestParams.delete("/checkbooks/432"));
    }

    @Test
    public void testGetOrgCheckbooks() throws Exception {
        checkbooks.getOrgCheckbooks(9988);

        verifyRequest(RequestParams.get("/orgs/9988/checkbooks"));
    }

    @Test
    public void testGetCheckbookEntries() throws Exception {
        checkbooks.getCheckbookEntries(5566);

        verifyRequest(RequestParams.get("/checkbooks/5566/entries"));
    }

    @Test
    public void testGetCheckbookEntry() throws Exception {
        checkbooks.getCheckbookEntry(678);

        verifyRequest(RequestParams.get("/checkbook_entries/678"));
    }

    @Test
    public void testCreateCheckbookEntry() throws Exception {
        String amount = "7.99";
        String description = "test create";
        checkbooks.createCheckbookEntry(765, amount, description);

        FluentStringsMap params = new FluentStringsMap().add("amount", amount).add("description", description);

        verifyRequest(RequestParams.post("/checkbooks/765/entries", params));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCheckbookEntryNullAmount() throws Exception {
        checkbooks.createCheckbookEntry(1, null, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCheckbookEntryNullDescription() throws Exception {
        checkbooks.createCheckbookEntry(1, "123", null);
    }

    @Test
    public void testUpdateCheckbookEntry() throws Exception {
        String amount = "7.99";
        String description = "test create";
        checkbooks.updateCheckbookEntry(765, amount, description);

        FluentStringsMap params = new FluentStringsMap().add("amount", amount).add("description", description);

        verifyRequest(RequestParams.put("/checkbook_entries/765", params));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCheckbookEntryNullAmount() throws Exception {
        checkbooks.updateCheckbookEntry(1, null, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCheckbookEntryNullDescription() throws Exception {
        checkbooks.updateCheckbookEntry(1, "123", null);
    }

    @Test
    public void testDeleteCheckbookEntry() throws Exception {
        checkbooks.deleteCheckbookEntry(575);

        verifyRequest(RequestParams.delete("/checkbook_entries/575"));

    }

}
