package com.orgsync.api;

public final class OrgSync {

    // So you can't instantiate or extend this class accidently
    private OrgSync() {
    }

    public static final ApiClient newApiClient(final String apiKey) {
        return newApiClient(apiKey, ApiClientImpl.DEFAULT_VERSION);
    }

    public static final ApiClient newApiClient(final String apiKey,
            final Version version) {
        return newApiClient(apiKey, version, ApiClientImpl.DEFAULT_HOST);
    }

    public static ApiClient newApiClient(final String apiKey,
            final Version version, final String host) {
        return new ApiClientImpl(apiKey, version, host);
    }

}
