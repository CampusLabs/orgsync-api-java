package com.orgsync.api;

import com.orgsync.api.model.ApiError;

/**
 * <p>
 * A response from the API, which may be either success or failure. Use the {@link #isSuccess()} method to determine if
 * this request was successful. If its a success, then {@link #getResult()} will return the result object. It its a
 * failure, then {@link #getError()} will return the {@link ApiError}.
 * 
 * @author steffyj
 * 
 * @param <T>
 *            the type of the successful response
 */
public interface ApiResponse<T> {

    /**
     * Return whether this response is a success or not. The {@link #getResult()} method only returns a value if this is
     * <code>true</code>.
     * 
     * @return <code>true</code> if this response is a success and <code>false</code> otherwise
     */
    public boolean isSuccess();

    /**
     * Get the result from this response if it {@link #isSuccess()}.
     * 
     * @return the response result, or <code>null</code> if this was an error.
     */
    public T getResult();

    /**
     * Get the error from this response if not {@link #isSuccess()}.
     * 
     * @return the api error, or <code>null</code> if this was a success.
     */
    public ApiError getError();

}
