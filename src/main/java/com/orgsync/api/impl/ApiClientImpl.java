package com.orgsync.api.impl;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.orgsync.api.ApiClient;
import com.orgsync.api.Version;

public class ApiClientImpl implements ApiClient {

	private final String apiKey;

	private final Version version;

	private AsyncHttpClient client;

	private Gson gson;

	public ApiClientImpl(final String apiKey, final Version version) {
		this.apiKey = apiKey;
		this.version = version;
	}

}
