package com.orgsync.api;

import java.util.List;

import com.orgsync.api.messages.Org;

public interface OrgsModule {

	public ApiResponse<List<Org>> getOrgs();

}
