package com.orgsync.api;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;

public class ApiClientImpl implements ApiClient {

	public static final String HOST = "https://api.orgsync.com/api/";

	public static final Version DEFAULT_VERSION = Version.V2;

	private final String apiKey;

	private final Version version;

	private AsyncHttpClient client;

	private Gson gson;

	public ApiClientImpl(final String apiKey, final Version version) {
		this.apiKey = apiKey;
		this.version = version;
	}

	@Override
	public <T> T getModule(final Module<T> module) {
		return module.get(this);
	}
}
