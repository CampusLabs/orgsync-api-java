package com.orgsync.api;

import com.ning.http.client.AsyncHttpClient;

public interface ApiClient {

    public void destroy();

    public <T> T getModule(Module<T> module);

    public Version getVersion();

    public String getApiKey();

    public AsyncHttpClient getHttpClient();

    public ApiClientImpl setHttpClient(AsyncHttpClient client);

}
