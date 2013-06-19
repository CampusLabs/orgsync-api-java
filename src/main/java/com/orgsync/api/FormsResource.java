package com.orgsync.api;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.forms.FormSubmission;

public interface FormsResource {

    public ListenableFuture<ApiResponse<FormSubmission>> getFormSubmission(int submissionId);

}
