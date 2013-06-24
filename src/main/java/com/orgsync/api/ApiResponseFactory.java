package com.orgsync.api;

import com.orgsync.api.model.ApiError;

/**
 * A factory for creating {@link ApiResponse}s. Use {@link #error(ApiError)} to create an error and use
 * {@link #success(Object)} to create a success.
 * 
 * @author steffyj
 * 
 */
/* package */final class ApiResponseFactory {

    private ApiResponseFactory() {
    }

    /**
     * Create an api response of type error.
     * 
     * @param error
     *            the error that was returned.
     * @return the api response
     */
    /* package */static final <T> ApiResponse<T> error(final ApiError error) {
        return new FailureResponse<T>(error);
    }

    /**
     * Get a success for the given result.
     * 
     * @param result
     *            the returned result
     * @return the api response
     */
    /* package */static final <T> ApiResponse<T> success(final T result) {
        return new SuccessResponse<T>(result);
    }

    private static final class FailureResponse<T> implements ApiResponse<T> {
        private final ApiError error;

        public FailureResponse(final ApiError error) {
            super();
            this.error = error;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public ApiError getError() {
            return error;
        }

        @Override
        public T getResult() {
            return null;
        }

    }

    private static final class SuccessResponse<T> implements ApiResponse<T> {
        private final T result;

        public SuccessResponse(final T result) {
            super();
            this.result = result;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public T getResult() {
            return result;
        }

        @Override
        public ApiError getError() {
            return null;
        }
    }

}
