package com.orgsync.api;

import java.util.List;

import com.orgsync.api.messages.Org;

/*package*/class OrgsModuleImpl implements OrgsModule {

	private final ApiClientImpl client;

	/* package */OrgsModuleImpl(final ApiClientImpl client) {
		this.client = client;
	}

	@Override
	public ApiResponse<List<Org>> getOrgs() {
		return null;
	}

}
