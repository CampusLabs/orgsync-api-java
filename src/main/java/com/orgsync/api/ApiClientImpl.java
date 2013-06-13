package com.orgsync.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.orgsync.api.messages.ApiError;

public class ApiClientImpl implements ApiClient {

	public static final String HOST = "https://api.orgsync.com/api/";

	public static final Version DEFAULT_VERSION = Version.V2;

	private static final AsyncHttpClient DEFAULT_CLIENT = new AsyncHttpClient();

	private static final Gson DEFAULT_GSON = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();

	private final String apiKey;

	private final Version version;

	private AsyncHttpClient client;

	private final Gson gson;

	public ApiClientImpl(final String apiKey, final Version version) {
		this.apiKey = apiKey;
		this.version = version;
		setHttpClient(DEFAULT_CLIENT);
		this.gson = DEFAULT_GSON;
	}

	@Override
	public void destroy() {
		getHttpClient().close();
	}

	@Override
	public <T> T getModule(final Module<T> module) {
		return module.get(this);
	}

	public AsyncHttpClient getHttpClient() {
		return client;
	}

	public ApiClientImpl setHttpClient(final AsyncHttpClient client) {
		this.client = client;
		return this;
	}

	public String getApiKey() {
		return apiKey;
	}

	/* package */<T> ListenableFuture<ApiResponse<T>> getResponse(
			final RequestParams requestParams, final Type type) {
		Request request = buildRequest(requestParams);
		try {
			return client.executeRequest(request,
					new ResponseCompletionHandler<ApiResponse<T>>(type));
		} catch (IOException e) {
			throw new ApiClientException(
					"Exception while making http request!", e);
		}
	}

	private Request buildRequest(final RequestParams requestParams) {
		return new RequestBuilder(requestParams.method)
				.setUrl(toURL(requestParams.endpoint))
				.setQueryParameters(mergeParams(requestParams.queryParams))
				.build();
	}

	private FluentStringsMap mergeParams(
			final Map<String, Collection<String>> queryParams) {
		return new FluentStringsMap(queryParams).add("key", apiKey);
	}

	private String toURL(final String endpoint) {
		return new StringBuilder().append(HOST).append(version.getPath())
				.append(endpoint).toString();
	}

	private class ResponseCompletionHandler<T> extends
			AsyncCompletionHandler<T> {

		private final Type type;

		public ResponseCompletionHandler(final Type type) {
			this.type = type;
		}

		@Override
		@SuppressWarnings("unchecked")
		public T onCompleted(final Response response) throws Exception {
			String body = response.getResponseBody();

			if (response.getStatusCode() == 200) {
				return (T) ApiResponse.success(gson.fromJson(body, type));
			}

			return (T) ApiResponse.error(gson.fromJson(body, ApiError.class));
		}

	}

}
