package com.orgsync.api;

import com.ning.http.client.FluentStringsMap;

/**
 * A representation of the API requests. This includes the method, endpoint, and any query params. The various
 * {@link #get(String)}, {@link #post(String, FluentStringsMap)}, {@link #delete(String)} factory method create a
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

    private final String method;
    private final String endpoint;
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
        return new RequestParams(GET, endpoint, params);
    }

    /**
     * Create a POST request to a given endpoint with the query params.
     * 
     * @param endpoint
     *            the endpoint to use
     * @param params
     *            the query params to pass
     * @return the new POST request
     */
    final static RequestParams post(final String endpoint,
            final FluentStringsMap params) {
        return new RequestParams(POST, endpoint, params);
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
        return new RequestParams(PUT, endpoint, params);
    }

    /**
     * Create a DELETE request to the given endpoint.
     * 
     * @param endpoint
     *            the endpoint to use
     * @return the new DELETE request
     */
    static RequestParams delete(final String endpoint) {
        return new RequestParams(DELETE, endpoint, NO_PARAMS);
    }

    private RequestParams(final String method, final String endpoint,
            final FluentStringsMap queryParams) {
        super();
        this.method = method;
        this.endpoint = endpoint;
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
     * The query params for the given request.
     * 
     * @return the query params
     */
    public final FluentStringsMap getQueryParams() {
        return queryParams;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((endpoint == null) ? 0 : endpoint.hashCode());
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        result = prime * result
                + ((queryParams == null) ? 0 : queryParams.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestParams other = (RequestParams) obj;
        if (endpoint == null) {
            if (other.endpoint != null)
                return false;
        } else if (!endpoint.equals(other.endpoint))
            return false;
        if (method == null) {
            if (other.method != null)
                return false;
        } else if (!method.equals(other.method))
            return false;
        if (queryParams == null) {
            if (other.queryParams != null)
                return false;
        } else if (!queryParams.equals(other.queryParams))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RequestParams [method=" + method + ", endpoint=" + endpoint
                + ", queryParams=" + queryParams + "]";
    }

}
