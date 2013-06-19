package com.orgsync.api;

import org.junit.Test;

public class FormsResourceImplTest extends BaseResourceTest {

    private final FormsResourceImpl resource = new FormsResourceImpl(client);

    @Test
    public void testGetFormSubmission() throws Exception {
        resource.getFormSubmission(123);

        verifyRequest(RequestParams.get("/form_submissions/123"));
    }

}
