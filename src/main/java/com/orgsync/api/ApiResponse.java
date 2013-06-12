package com.orgsync.api;

import com.orgsync.api.messages.ApiError;

public class ApiResponse<T> {

    private final T result;
    private final ApiError error;

    /* pacakge */ApiResponse(final Object result, final ApiError error) {
        this.result = (T) result;
        this.error = error;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public final T getResult() {
        return result;
    }

    public final ApiError getError() {
        return error;
    }

}
