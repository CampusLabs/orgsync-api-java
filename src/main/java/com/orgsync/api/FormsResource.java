package com.orgsync.api;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.forms.Form;
import com.orgsync.api.model.forms.FormSubmission;

public interface FormsResource {

    public ListenableFuture<ApiResponse<Form>> getForms();

    public ListenableFuture<ApiResponse<Form>> getOrgForms(int orgId);

    public ListenableFuture<ApiResponse<Form>> getForm(int formId);

    public ListenableFuture<ApiResponse<FormSubmission>> getFormSubmission(int submissionId);

}
