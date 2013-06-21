package com.orgsync.api;

public final class OrgSync {

    // So you can't instantiate or extend this class accidently
    private OrgSync() {
    }

    public static final ApiClient newApiClient(final String apiKey) {
        return newApiClient(apiKey);
    }

    public static ApiClient newApiClient(final String apiKey, final String host) {
        return new ApiClientImpl(apiKey, host);
    }

}
