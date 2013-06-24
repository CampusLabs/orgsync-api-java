package com.orgsync.api;

import java.util.List;

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
    public ListenableFuture<ApiResponse<FormSubmission>> getFormSubmission(final int submissionId) {
        return getResponse(RequestParams.get("/form_submissions/" + submissionId), FormSubmission.TYPE);
    }

}
