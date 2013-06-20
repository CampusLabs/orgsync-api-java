package com.orgsync.api;

import com.ning.http.client.FluentStringsMap;

/*package*/class RequestParams {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final FluentStringsMap NO_PARAMS = new FluentStringsMap();

    private final String method;
    private final String endpoint;
    private final FluentStringsMap queryParams;

    final static RequestParams get(final String endpoint) {
        return get(endpoint, NO_PARAMS);
    }

    final static RequestParams get(final String endpoint,
            final FluentStringsMap params) {
        return new RequestParams(GET, endpoint, params);
    }

    final static RequestParams post(final String endpoint,
            final FluentStringsMap params) {
        return new RequestParams(POST, endpoint, params);
    }

    public static RequestParams delete(final String endpoint) {
        return new RequestParams(DELETE, endpoint, NO_PARAMS);
    }

    private RequestParams(final String method, final String endpoint,
            final FluentStringsMap queryParams) {
        super();
        this.method = method;
        this.endpoint = endpoint;
        this.queryParams = queryParams;
    }

    public final String getMethod() {
        return method;
    }

    public final String getEndpoint() {
        return endpoint;
    }

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
