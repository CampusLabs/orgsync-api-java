package com.orgsync.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;

public class ApiClientImplTest {

	private final String apiKey = "test";
	private final Version version = ApiClientImpl.DEFAULT_VERSION;
	private final AsyncHttpClient http = mock(AsyncHttpClient.class);
	private final ApiClientImpl client = new ApiClientImpl(apiKey, version)
			.setHttpClient(http);

	@Test
	public void testGetApiKey() throws Exception {
		assertEquals(apiKey, client.getApiKey());
	}

	@Test
	public void testGetClient() throws Exception {
		assertEquals(http, client.getHttpClient());
	}

	@Test
	public void testDestroy() {
		client.destroy();
		verify(http).close();
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetModule() throws Exception {
		Module module = mock(Module.class);
		client.getModule(module);
		verify(module).get(client);
	}

	@Test
	public void testGetResponse() throws Exception {
		String endpoint = "/test";
		ListenableFuture<ApiResponse<String>> response = client.getResponse(
				RequestParams.get(endpoint), new TypeToken<String>() {
				}.getType());

	}
}
