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

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.forms.Form;
import com.orgsync.api.model.forms.FormSubmission;

/**
 * The forms resource implementation.
 * 
 * @author steffyj
 * 
 */
/* package */class FormsResourceImpl extends BaseResource implements FormsResource {

    /* package */FormsResourceImpl(final ApiClientImpl client) {
        super(client, "/forms");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Form>>> getForms() {
        return list(Form.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Form>>> getOrgForms(final int orgId) {
        String prefix = String.format("/orgs/%d", orgId);
        return list(prefix, Form.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Form>> getForm(final int formId) {
        return show(formId, Form.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Form>> getForm(int formId, String status) {
        FluentStringsMap params = new FluentStringsMap().add("status", status);
        return show(formId, params, Form.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<FormSubmission>> getFormSubmission(final int submissionId) {
        return getResponse(RequestParams.get("/form_submissions/" + submissionId), FormSubmission.TYPE);
    }

}
