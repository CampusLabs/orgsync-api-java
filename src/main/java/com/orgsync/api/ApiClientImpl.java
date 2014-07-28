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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.orgsync.api.model.ApiError;

/**
 * The API client which handles returning resources and providing a uniform interface to make requests of the API and
 * translate the responses.
 * 
 * 
 * @author steffyj
 * 
 */
/* package */class ApiClientImpl implements ApiClient {

    private static final Logger log = LoggerFactory
            .getLogger(ApiClientImpl.class);

    public static final String DEFAULT_HOST = "https://api.orgsync.com/api/v2";

    private final String apiKey;

    private final String host;
    private final Properties props;

    private AsyncHttpClient client;

    /* package */ApiClientImpl(final String apiKey, final String host, final Properties props) {
        this.host = host;
        this.apiKey = apiKey;
        this.props = props;
        setHttpClient(new AsyncHttpClient());
    }

    @Override
    public void destroy() {
        closeHttpClient();
    }

    private void closeHttpClient() {
        if (getHttpClient() != null) {
            getHttpClient().close();
        }

    }

    @Override
    public <T> T getResource(final Resource<T> resource) {
        return resource.get(this);
    }

    @Override
    public AsyncHttpClient getHttpClient() {
        return client;
    }
    
    /* package */Properties getProps() {
        return props;
    }

    @Override
    public ApiClientImpl setHttpClient(final AsyncHttpClient client) {
        Util.checkNotNull(client);

        closeHttpClient();

        this.client = client;
        return this;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Take a given request and type and return a listenable future of {@link ApiResponse} for that request. This
     * handles making the request, translating the response, and returning a future.
     * 
     * @throws ApiClientException
     *             if the http client throws an {@link IOException}
     * 
     * @param requestParams
     *            the request information
     * @param type
     *            the type of the returned success response
     * 
     * @return the future to the response of this request
     */
    /* package */<T> ListenableFuture<ApiResponse<T>> getResponse(
            final RequestParams requestParams, final Type type) {
        log.debug("Call with request params: {}", requestParams);

        Request request = buildRequest(requestParams);

        try {
            log.debug("Executing request: {}", request);
            return client.executeRequest(request,
                    new ResponseCompletionHandler<ApiResponse<T>>(type));
        } catch (IOException e) {
            log.error("IOException making http request (message={})! "
                    + " Throwing ApiClientException!", e.getMessage());
            throw new ApiClientException(
                    "Exception while making http request!", e);
        }
    }

    /**
     * Build a request from the given request parameters.
     * 
     * @param requestParams
     *            the request information
     * @return the fully built request
     */
    private Request buildRequest(final RequestParams requestParams) {
        return new RequestBuilder(requestParams.getMethod())
                .setBody(requestParams.getBody())
                .setUrl(toURL(requestParams.getEndpoint()))
                .setQueryParameters(mergeParams(requestParams.getQueryParams()))
                .build();
    }

    /**
     * Add the api key to the query params.
     * 
     * @param queryParams
     *            the existing query params
     * @return the query params with the key added
     */
    private FluentStringsMap mergeParams(final FluentStringsMap queryParams) {
        return new FluentStringsMap(queryParams).add("key", apiKey);
    }

    /**
     * Take an endpoint and return a full URL to that endpoint.
     * 
     * @param endpoint
     *            the endpoint to use
     * @return the full URL to that endpoint
     */
    private String toURL(final String endpoint) {
        return new StringBuilder().append(host).append(endpoint).toString();
    }

    /**
     * A {@link AsyncCompletionHandler} which takes a response and translates it to an {@link ApiResponse} success with
     * the value if we get a 200, and an {@link ApiResponse} failure if we get a non-200 response.
     * <p>
     * This allows us to return a future but translates to the correct type.
     * 
     * @author steffyj
     * 
     * @param <T>
     *            the type of the object that a success response represents
     */
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
            int status = response.getStatusCode();
            log.debug("Received response with status={}, body={}", status, body);

            if (status >= 200 && status < 300) {
                return (T) ApiResponseFactory
                        .success(status, JsonSerializer.fromJson(body, type));
            }

            return (T) ApiResponseFactory
                        .error(status, JsonSerializer.fromJson(body, ApiError.class));
        }

    }

}
