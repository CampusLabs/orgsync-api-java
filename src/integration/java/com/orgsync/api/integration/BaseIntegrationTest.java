package com.orgsync.api.integration;

import org.junit.AfterClass;

import com.orgsync.api.ApiClient;
import com.orgsync.api.OrgSync;
import com.orgsync.api.Resource;

public class BaseIntegrationTest<T> {

    public static final String API_KEY = DbTemplate.getString("api_key");
    public static final String HOST = "http://localhost:8080/api/v2";

    static final ApiClient client = OrgSync.newApiClient(API_KEY, HOST);
    private final T resource;

    public BaseIntegrationTest(final Resource<T> resourceKey) {
        resource = client.getResource(resourceKey);
    }

    public T getResource() {
        return resource;
    }

    @AfterClass
    public static void teardown() {
        client.destroy();
    }

}
