package com.orgsync.api;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ning.http.client.ListenableFuture;

/*package*/abstract class BaseResource {

    /* package */static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private final ApiClientImpl client;

    /* package */BaseResource(final ApiClientImpl client) {
        this.client = client;
    }

    /* package */<T> ListenableFuture<ApiResponse<T>> getResponse(final RequestParams params, final Type type) {
        return client.getResponse(params, type);
    }

    /* package */String dateToQueryParam(final Date date) {
        return dateFormat.format(date);
    }

}
