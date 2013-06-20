package com.orgsync.api;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.forms.Form;
import com.orgsync.api.model.forms.FormSubmission;

/*package*/class FormsResourceImpl extends BaseResource implements FormsResource {

    /* package */FormsResourceImpl(final ApiClientImpl client) {
        super(client, "/forms");
    }

    @Override
    public ListenableFuture<ApiResponse<Form>> getForms() {
        return getResponse(RequestParams.get("/forms"), new TypeToken<Form>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Form>> getOrgForms(final int orgId) {
        String endpoint = String.format("/orgs/%d/forms", orgId);
        return getResponse(RequestParams.get(endpoint), new TypeToken<Form>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Form>> getForm(final int formId) {
        return getResponse(RequestParams.get("/forms/" + formId), new TypeToken<Form>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<FormSubmission>> getFormSubmission(final int submissionId) {
        return getResponse(
                RequestParams.get("/form_submissions/" + submissionId),
                new TypeToken<FormSubmission>() {
                }.getType());
    }

}
