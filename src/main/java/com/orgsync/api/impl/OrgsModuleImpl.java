package com.orgsync.api.impl;

import java.util.List;

import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgsModule;
import com.orgsync.api.messages.Org;

public class OrgsModuleImpl implements OrgsModule {

	private final ApiClientImpl client;

	public OrgsModuleImpl(final ApiClientImpl client) {
		this.client = client;
	}

	@Override
	public ApiResponse<List<Org>> getOrgs() {
		return null;
	}

}
