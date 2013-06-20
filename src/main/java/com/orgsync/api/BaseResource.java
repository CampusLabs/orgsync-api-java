package com.orgsync.api;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ning.http.client.ListenableFuture;

/*package*/abstract class BaseResource {

    /* package */static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private final ApiClientImpl client;

    private final String endpoint;

    /* package */BaseResource(final ApiClientImpl client, final String endpoint) {
        this.client = client;
        this.endpoint = endpoint;
    }

    /* package */<T> ListenableFuture<ApiResponse<T>> getResponse(final RequestParams params, final Type type) {
        return client.getResponse(params, type);
    }

    /* package */String dateToQueryParam(final Date date) {
        return dateFormat.format(date);
    }

    String getEndpoint() {
        return endpoint;
    }

    String shorFor(final int id) {
        return String.format("%s/%d", endpoint, id);
    }

    <T> ListenableFuture<ApiResponse<T>> list(final Type type) {
        return getResponse(RequestParams.get(endpoint), type);
    }

    <T> ListenableFuture<ApiResponse<T>> list(final String prefix, final Type type) {
        return getResponse(RequestParams.get(prefix + endpoint), type);
    }

}
