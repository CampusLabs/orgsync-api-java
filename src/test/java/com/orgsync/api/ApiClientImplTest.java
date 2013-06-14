package com.orgsync.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Request;

public class ApiClientImplTest {

	private final String apiKey = "test";
	private final String endpoint = "/test";
	private final Type type = new TypeToken<String>() {
	}.getType();
	private final Version version = ApiClientImpl.DEFAULT_VERSION;
	private final String host = ApiClientImpl.DEFAULT_HOST;

	private AsyncHttpClient http;
	private ApiClientImpl client;

	@Before
	public void setup() {
		http = mock(AsyncHttpClient.class);
		client = new ApiClientImpl(apiKey, version, host).setHttpClient(http);
	}

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

	@SuppressWarnings("unchecked")
	@Test(expected = ApiClientException.class)
	public void testGetResponseFailsForHttpException() throws Exception {

		RequestParams params = RequestParams.get(endpoint);

		when(http.executeRequest(any(Request.class), any(AsyncHandler.class)))
				.thenThrow(new IOException("bang"));

		client.getResponse(params, type);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testSucessfulRequest() throws Exception {
		ListenableFuture<ApiResponse<String>> expected = mock(ListenableFuture.class);

		RequestParams params = RequestParams.get(endpoint);

		when(http.executeRequest(any(Request.class), any(AsyncHandler.class)))
				.thenReturn(expected);

		ListenableFuture<ApiResponse<Object>> result = client.getResponse(
				params, type);

		assertEquals(expected, result);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testCreatesCorrectRequest() throws Exception {
		FluentStringsMap queryParams = new FluentStringsMap()
				.add("test", "foo");
		RequestParams params = RequestParams.get(endpoint, queryParams);

		ArgumentCaptor<Request> captor = ArgumentCaptor.forClass(Request.class);
		when(http.executeRequest(captor.capture(), any(AsyncHandler.class)))
				.thenReturn(null);

		client.getResponse(params, type);

		Request request = captor.getValue();
		assertEquals("GET", request.getMethod());

		URL url = new URL(request.getUrl());
		assertEquals("api.orgsync.com", url.getHost());
		assertEquals("/api/" + version.getPath() + endpoint, url.getPath());

		Set<String> qParams = new HashSet<String>(Arrays.asList(url.getQuery()
				.split("&")));
		assertEquals(
				new HashSet<String>(Arrays.asList("key=" + apiKey, "test=foo")),
				qParams);
	}
}
