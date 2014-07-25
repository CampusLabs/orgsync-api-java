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
 * A factory for creating {@link ApiResponse}s. Use {@link #error(int, ApiError)} to create an error and use
 * {@link #success(int, Object)} to create a success.
 * 
 * @author steffyj
 * 
 */
/* package */final class ApiResponseFactory {

    private ApiResponseFactory() {
    }

    /**
     * Create an api response error from an error message.
     *
     * @param status The status to return
     * @param error the error message
     * @param <T> The type of the expected response
     * @return The failed api response
     */
    /* package */static final <T> BaseApiResponse<T> error(final int status, final String error) {
        return new FailureResponse<T>(status, new ApiError(error));
    }

    /**
     * Create an api response error from an ApiError.
     *
     * @param status The status to return
     * @param error the error to use
     * @param <T> The type of the expected response
     * @return The failed api response
     */
    /* package */static final <T> BaseApiResponse<T> error(final int status, final ApiError error) {
        return new FailureResponse<T>(status, error);
    }

    /**
     * Create an error response from the given error reponse.
     *
     * @param response
     *              The existing failed response to wrap
     * @param <T>   The type of the result
     * @return The new error response with the correct type and the same error
     *
     * @throws java.lang.RuntimeException if the given response is not a failure
     */
    /* package */static final <T> BaseApiResponse<T> error(ApiResponse<?> response) {
        if (response.isSuccess()) {
            throw new RuntimeException("Should only be called with a failed ApiResponse!");
        }

        return error(response.getStatus(), response.getError());
    }

    /**
     * Get a success for the given result.
     * 
     * @param result
     *            the returned result
     * @return the api response
     */
    /* package */static final <T> BaseApiResponse<T> success(final int status, final T result) {
        return new SuccessResponse<T>(status, result);
    }

    private static final class FailureResponse<T> extends BaseApiResponse<T> implements ApiResponse<T> {
        private final ApiError error;

        public FailureResponse(final int status, final ApiError error) {
            super(status);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FailureResponse)) return false;
            if (!super.equals(o)) return false;

            FailureResponse that = (FailureResponse) o;

            if (error != null ? !error.equals(that.error) : that.error != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (error != null ? error.hashCode() : 0);
            return result;
        }

        @Override
        <S> BaseApiResponse<S> map(MapFunction<T, S> f) {
            return ApiResponseFactory.error(this);
        }

        @Override
        <S> BaseApiResponse<S> flatMap(MapFunction<T, BaseApiResponse<S>> f) {
            return ApiResponseFactory.error(this);
        }
    }

    private static final class SuccessResponse<T> extends BaseApiResponse<T> implements ApiResponse<T> {
        private final T result;

        public SuccessResponse(final int status, final T result) {
            super(status);
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

        @Override
        <S> BaseApiResponse<S> map(MapFunction<T, S> f) {
            BaseApiResponse<S> response = null;

            try {
                response = ApiResponseFactory.success(getStatus(), f.f(getResult()));
            } catch (Exception e) {
                e.printStackTrace();
                response = ApiResponseFactory.error(500, "Exception caught: " + e.getMessage());
            }

            return response;
        }

        @Override
        <S> BaseApiResponse<S> flatMap(MapFunction<T, BaseApiResponse<S>> f) {
            try {
                return f.f(getResult());
            } catch (Exception e) {
                e.printStackTrace();
                return ApiResponseFactory.error(500, "Exception caught: " + e.getMessage());
            }
        }
    }

}
