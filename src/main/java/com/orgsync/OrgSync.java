package com.orgsync;

import com.orgsync.api.ApiClient;
import com.orgsync.api.Version;
import com.orgsync.api.impl.ApiClientImpl;

public final class OrgSync {

	private OrgSync() {
	}

	public static final ApiClient newApiClient(final String apiKey) {
		return newApiClient(apiKey, ApiClient.DEFAULT_VERSION);
	}

	public static final ApiClient newApiClient(final String apiKey,
			final Version version) {
		return new ApiClientImpl(apiKey, version);
	}

}
