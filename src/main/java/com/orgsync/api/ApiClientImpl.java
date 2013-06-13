package com.orgsync.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.orgsync.api.messages.ApiError;

public class ApiClientImpl implements ApiClient {

	public static final String HOST = "https://api.orgsync.com/api/";

	public static final Version DEFAULT_VERSION = Version.V2;

	private final String apiKey;

	private final Version version;

	private final AsyncHttpClient client;

	private final Gson gson;

	public ApiClientImpl(final String apiKey, final Version version) {
		this.apiKey = apiKey;
		this.version = version;
		this.client = new AsyncHttpClient();
		this.gson = createGson();
	}

	@Override
	public void destroy() {
		getClient().close();
	}

	@Override
	public <T> T getModule(final Module<T> module) {
		return module.get(this);
	}

	/* package */<T> ApiResponse<T> getResponse(final String endpoint,
			final Type type) {
		Response result = makeCall(endpoint);
		String body = null;
		try {
			body = result.getResponseBody();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result.getStatusCode() == 200 && body != null) {
			return new ApiResponse<T>(gson.fromJson(body, type), null);
		} else {
			return new ApiResponse<T>(null, gson.fromJson(body, ApiError.class));
		}
	}

	private Response makeCall(final String endpoint) {

		try {
			String url = toURL(endpoint);
			System.out.println("Sending request to: " + url);
			return getClient().prepareGet(url).execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private AsyncHttpClient getClient() {
		return client;
	}

	private Gson createGson() {
		return new Gson();
	}

	// TODO gots to go...
	private String toURL(final String endpoint) {
		return new StringBuilder().append(HOST).append(version.getPath())
				.append(endpoint).append("?key=").append(apiKey).toString();
	}

}
