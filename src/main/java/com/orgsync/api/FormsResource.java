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

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.forms.Form;
import com.orgsync.api.model.forms.FormSubmission;

/**
 * <p>
 * Access to the forms resource from the OrgSync API. This allows a client to get all forms, get forms for an org, get a
 * single form, and get a form submission.
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/forms">https://api.orgsync.com/api/docs/v2/forms</a>
 * 
 * @author steffyj
 * 
 */
public interface FormsResource {

    /**
     * Get all of the forms for this community.
     * 
     * @return a future to the response with all forms
     */
    public ListenableFuture<ApiResponse<List<Form>>>
            getForms();

    /**
     * Get all of the forms for a single org.
     * 
     * @param orgId
     *            the org to get forms for
     * @return a future to the response of forms
     */
    public ListenableFuture<ApiResponse<List<Form>>>
            getOrgForms(int orgId);

    /**
     * Get a single form.
     * 
     * @param formId
     *            the id of the form to get
     * @return a future to the response with the form
     */
    public ListenableFuture<ApiResponse<Form>>
            getForm(int formId);

    /**
     * A the form submission with the given id.
     * 
     * @param submissionId
     *            the id of the form submission to get
     * @return a future to a response with the form submission
     */
    public ListenableFuture<ApiResponse<FormSubmission>>
            getFormSubmission(int submissionId);

}
