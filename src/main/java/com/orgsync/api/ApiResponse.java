package com.orgsync.api;

import com.orgsync.api.messages.ApiError;

public interface ApiResponse<T> {

    public boolean isSuccess();

    public T getResult();

    public ApiError getError();

}
