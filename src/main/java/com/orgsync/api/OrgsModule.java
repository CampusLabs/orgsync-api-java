package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.messages.orgs.Org;

public interface OrgsModule {

	public ListenableFuture<ApiResponse<List<Org>>> getOrgs();

}
