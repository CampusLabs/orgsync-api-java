package com.orgsync.api;

import java.io.IOException;
import java.lang.reflect.Type;

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

/*package*/class ApiClientImpl implements ApiClient {

    private static final Logger log = LoggerFactory
            .getLogger(ApiClientImpl.class);

    public static final String DEFAULT_HOST = "https://api.orgsync.com/api/";

    public static final Version DEFAULT_VERSION = Version.V2;

    private static final AsyncHttpClient DEFAULT_CLIENT = new AsyncHttpClient();

    private static final Gson DEFAULT_GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private final String apiKey;

    private final String host;
    private final Version version;

    private AsyncHttpClient client;

    private final Gson gson;

    /* package */ApiClientImpl(final String apiKey, final Version version,
            final String host) {
        this.host = host;
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
    public <T> T getResource(final Resource<T> resource) {
        return resource.get(this);
    }

    @Override
    public AsyncHttpClient getHttpClient() {
        return client;
    }

    @Override
    public ApiClientImpl setHttpClient(final AsyncHttpClient client) {
        this.client = client;
        return this;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public Version getVersion() {
        return version;
    }

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

    private Request buildRequest(final RequestParams requestParams) {
        return new RequestBuilder(requestParams.getMethod())
                .setUrl(toURL(requestParams.getEndpoint()))
                .setQueryParameters(mergeParams(requestParams.getQueryParams()))
                .build();
    }

    private FluentStringsMap mergeParams(final FluentStringsMap queryParams) {
        return new FluentStringsMap(queryParams).add("key", apiKey);
    }

    private String toURL(final String endpoint) {
        return new StringBuilder().append(host).append(version.getPath())
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
            log.debug("Received response string: {}", body);

            if (response.getStatusCode() == 200) {
                return (T) ApiResponseFactory.success(gson.fromJson(body, type));
            }

            return (T) ApiResponseFactory.error(gson.fromJson(body, ApiError.class));
        }

    }

}
