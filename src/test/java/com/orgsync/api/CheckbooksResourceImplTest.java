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
        checkbooks.createCheckbook(3345, name);

        FluentStringsMap params = new FluentStringsMap().add("name", name);

        verifyRequest(RequestParams.post("/orgs/3345/checkbooks", params));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCheckbookWithNull() throws Exception {
        checkbooks.createCheckbook(123, null);
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
