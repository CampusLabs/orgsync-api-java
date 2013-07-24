package com.orgsync.api.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.Resource;

public class BaseIntegrationTest<T> {

    public static final String API_KEY = DbTemplate.getString("api_key");
    public static final String HOST = "http://localhost:8080/api/v2";

    private static final Map<Resource<?>, ApiClient> clients = new HashMap<Resource<?>, ApiClient>();

    final ApiClient client = OrgSync.newApiClient(API_KEY, HOST);
    private final T resource;

    public BaseIntegrationTest(final Resource<T> resourceKey) {
        resource = client.getResource(resourceKey);
        clients.put(resourceKey, client);
    }

    public T getResource() {
        return resource;
    }

    public <R> R getResult(final ListenableFuture<ApiResponse<R>> future) throws InterruptedException,
            ExecutionException {
        return future.get().getResult();
    }

    public static void cleanup(final Resource<?> resourceKey) {
        clients.get(resourceKey).destroy();
    }

}
