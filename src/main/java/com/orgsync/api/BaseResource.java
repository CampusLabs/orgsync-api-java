package com.orgsync.api;

/*package*/abstract class BaseResource {

    private final ApiClientImpl client;

    /* package */BaseResource(final ApiClientImpl client) {
        this.client = client;
    }

    public ApiClientImpl getClient() {
        return client;
    }
}
