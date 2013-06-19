package com.orgsync.api;

import java.lang.reflect.Type;

import com.ning.http.client.ListenableFuture;

/*package*/abstract class BaseResource {

    private final ApiClientImpl client;

    /* package */BaseResource(final ApiClientImpl client) {
        this.client = client;
    }

    protected <T> ListenableFuture<ApiResponse<T>> getResponse(final RequestParams params, final Type type) {
        return client.getResponse(params, type);
    }
}
