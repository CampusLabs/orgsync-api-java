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

	// /* package */<T> ApiResponse<T> getResponse(final String method,
	// final String endpoint, final Type type) {
	// RequestBuilder result = makeCall(endpoint);
	// String body = null;
	// try {
	// body = result.getResponseBody();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if (result.getStatusCode() == 200 && body != null) {
	// return ApiResponse.success(gson.fromJson(body, type));
	// } else {
	// return ApiResponse.error(gson.fromJson(body, ApiError.class));
	// }
	// }
	//
	// private Response makeCall(final String endpoint) {
	//
	// try {
	// String url = toURL(endpoint);
	// System.out.println("Sending request to: " + url);
	// return getHttpClient().prepareGet(url).execute().get();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (ExecutionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

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
					new ResponseCompletionHandler<ApiResponse<T>>());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO obviously not good
		return null;
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

	// TODO gots to go...
	private String toURL(final String endpoint) {
		return new StringBuilder().append(HOST).append(version.getPath())
				.append(endpoint).toString();
	}

	private class ResponseCompletionHandler<T> extends
			AsyncCompletionHandler<T> {

		@Override
		public T onCompleted(final Response response) throws Exception {
			return null;
		}

	}

}
