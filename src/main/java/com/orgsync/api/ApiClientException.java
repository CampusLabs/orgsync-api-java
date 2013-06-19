package com.orgsync.api;

/**
 * An exception in the {@link ApiClient} that has caused a failure to retrieve the requested information. The
 * {@link #getCause()} will return the exception that caused this failure.
 * 
 * @author steffyj
 * 
 */
@SuppressWarnings("serial")
public class ApiClientException extends RuntimeException {

    /* package */ApiClientException(final String message, final Exception e) {
        super(message, e);
    }
}
