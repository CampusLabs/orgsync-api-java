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

import com.ning.http.client.FluentStringsMap;
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
    public void testGetFormWithStatus() throws Exception {
        resource.getForm(587, "testing");
        verifyRequest(RequestParams.get("/forms/587", new FluentStringsMap().add("status", "testing")));
    }

    @Test
    public void testGetFormSubmission() throws Exception {
        resource.getFormSubmission(123);

        verifyRequest(RequestParams.get("/form_submissions/123"));
    }

}
