package com.orgsync.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/*package*/class RequestParams {

	static final String GET = "GET";
	static final String PUT = "PUT";
	static final Map<String, Collection<String>> NO_PARAMS = Collections
			.emptyMap();

	final String method;
	final String endpoint;
	final Map<String, Collection<String>> queryParams;

	final static RequestParams get(final String endpoint) {
		return get(endpoint, NO_PARAMS);
	}

	final static RequestParams get(final String endpoint,
			final Map<String, Collection<String>> params) {
		return new RequestParams(GET, endpoint, params);
	}

	RequestParams(final String method, final String endpoint,
			final Map<String, Collection<String>> queryParams) {
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

	public final Map<String, Collection<String>> getQueryParams() {
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
