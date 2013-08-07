/*
 * Copyright 2013 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
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
