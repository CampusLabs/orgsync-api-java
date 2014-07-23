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

import com.ning.http.client.FluentStringsMap;

/**
 * A representation of the API requests. This includes the method, endpoint, and any query params. The various
 * {@link #get(String)}, {@link #post(String, String, FluentStringsMap)}, {@link #delete(String)} factory method create a
 * request of the given method.
 * 
 * @author steffyj
 * 
 */
/* package */class RequestParams {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final FluentStringsMap NO_PARAMS = new FluentStringsMap();
    private static final String NO_BODY = "";

    private final String method;
    private final String endpoint;
    private final String body;
    private final FluentStringsMap queryParams;

    /**
     * Create a GET request to the given endpoint.
     * 
     * @param endpoint
     *            the endpoint to use
     * @return the GET request
     */
    final static RequestParams get(final String endpoint) {
        return get(endpoint, NO_PARAMS);
    }

    /**
     * Create a GET request to an endpoint with the given query params.
     * 
     * @param endpoint
     *            the endpoint to use
     * @param params
     *            the query params to pass
     * @return the GET request
     */
    final static RequestParams get(final String endpoint,
            final FluentStringsMap params) {
        return new RequestParams(GET, endpoint, NO_BODY, params);
    }

    /**
     * Create a POST request to a given endpoint with the given body
     *
     * @param endpoint
     *            the endpoint to use
     * @param params
     *            the query params to pass
     * @return the new POST request
     */
    final static RequestParams post(final String endpoint, final FluentStringsMap params) {
        return post(endpoint, NO_BODY, params);
    }

    /**
     * Create a POST request to a given endpoint with the body and query params.
     * 
     * @param endpoint
     *            the endpoint to use
     * @param params
     *            the query params to pass
     * @return the new POST request
     */
    final static RequestParams post(final String endpoint, final String body,
            final FluentStringsMap params) {
        return new RequestParams(POST, endpoint, body, params);
    }

    /**
     * Create a PUT request to an endpoint with the given query params.
     * 
     * @param endpoint
     *            the endpoint to use
     * @param params
     *            the query params to pass
     * @return the new PUT request
     */
    static RequestParams put(final String endpoint, final FluentStringsMap params) {
        return new RequestParams(PUT, endpoint, NO_BODY, params);
    }

    /**
     * Create a DELETE request to the given endpoint.
     * 
     * @param endpoint
     *            the endpoint to use
     * @return the new DELETE request
     */
    static RequestParams delete(final String endpoint) {
        return new RequestParams(DELETE, endpoint, NO_BODY, NO_PARAMS);
    }

    private RequestParams(final String method, final String endpoint,
            final String body, final FluentStringsMap queryParams) {
        super();
        this.method = method;
        this.endpoint = endpoint;
        this.body = body;
        this.queryParams = queryParams;
    }

    /**
     * The method of this request (e.g. GET, PUT, etc.).
     * 
     * @return the method
     */
    public final String getMethod() {
        return method;
    }

    /**
     * The path to the endpoint to hit (e.g. /accounts/123).
     * 
     * @return the endpoint path
     */
    public final String getEndpoint() {
        return endpoint;
    }

    /**
     * The body of the request (for PUT or POST).
     *
     * @return the body
     */
    public final String getBody() {
        return body;
    }

    /**
     * The query params for the given request.
     * 
     * @return the query params
     */
    public final FluentStringsMap getQueryParams() {
        return queryParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestParams)) return false;

        RequestParams that = (RequestParams) o;

        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (endpoint != null ? !endpoint.equals(that.endpoint) : that.endpoint != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (queryParams != null ? !queryParams.equals(that.queryParams) : that.queryParams != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (endpoint != null ? endpoint.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (queryParams != null ? queryParams.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestParams{");
        sb.append("method='").append(method).append('\'');
        sb.append(", endpoint='").append(endpoint).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", queryParams=").append(queryParams);
        sb.append('}');
        return sb.toString();
    }
}
