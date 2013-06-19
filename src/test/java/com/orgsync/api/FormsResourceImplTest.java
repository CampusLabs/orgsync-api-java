package com.orgsync.api;

import org.junit.Test;

public class FormsResourceImplTest extends BaseResourceTest {

    private final FormsResourceImpl resource = new FormsResourceImpl(client);

    @Test
    public void testGetForms() throws Exception {
        resource.getForms();
        verifyRequest(RequestParams.get("/forms"));
    }

    @Test
    public void testGetOrgForms() throws Exception {
        resource.getOrgForms(456);
        verifyRequest(RequestParams.get("/orgs/456/forms"));
    }

    @Test
    public void testGetForm() throws Exception {
        resource.getForm(587);
        verifyRequest(RequestParams.get("/forms/587"));
    }

    @Test
    public void testGetFormSubmission() throws Exception {
        resource.getFormSubmission(123);

        verifyRequest(RequestParams.get("/form_submissions/123"));
    }

}
