/*
 * Copyright 2013 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orgsync.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
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
    private final String host = ApiClientImpl.DEFAULT_HOST;

    private AsyncHttpClient http;
    private ApiClientImpl client;

    @Before
    public void setup() {
        http = mock(AsyncHttpClient.class);
        client = new ApiClientImpl(apiKey, host, new Properties()).setHttpClient(http);
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
    public void testSetHttpClient() throws Exception {
        AsyncHttpClient newClient = mock(AsyncHttpClient.class);

        client.setHttpClient(newClient);

        assertSame(newClient, client.getHttpClient());

        client.destroy();

        verify(http).close();
        verify(newClient).close();
    }

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testGetResource() throws Exception {
        Resource resource = mock(Resource.class);
        client.getResource(resource);
        verify(resource).get(client);
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
        assertEquals("/api/v2" + endpoint, url.getPath());

        Set<String> qParams = new HashSet<String>(Arrays.asList(url.getQuery()
                .split("&")));
        assertEquals(
                new HashSet<String>(Arrays.asList("key=" + apiKey, "test=foo")),
                qParams);
    }
}
