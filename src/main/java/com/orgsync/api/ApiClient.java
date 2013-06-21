package com.orgsync.api;

import com.ning.http.client.AsyncHttpClient;

public interface ApiClient {

    public void destroy();

    public <T> T getResource(Resource<T> resource);

    public String getApiKey();

    public AsyncHttpClient getHttpClient();

    public ApiClientImpl setHttpClient(AsyncHttpClient client);

}
