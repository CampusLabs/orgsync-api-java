package com.orgsync.api;

@SuppressWarnings("serial")
public class ApiClientException extends RuntimeException {

    public ApiClientException(final String message, final Exception e) {
        super(message, e);
    }
}
