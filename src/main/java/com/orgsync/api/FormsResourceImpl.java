package com.orgsync.api;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.forms.FormSubmission;

/*package*/class FormsResourceImpl extends BaseResource implements FormsResource {

    /* package */FormsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<FormSubmission>> getFormSubmission(final int submissionId) {
        return getResponse(
                RequestParams.get("/form_submissions/" + submissionId),
                new TypeToken<FormSubmission>() {
                }.getType());
    }

}
