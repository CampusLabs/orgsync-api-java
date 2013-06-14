package com.orgsync.api;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.messages.Success;
import com.orgsync.api.messages.orgs.AddAccounts;
import com.orgsync.api.messages.orgs.Org;

/*package*/class OrgsModuleImpl implements OrgsModule {

	private final ApiClientImpl client;

	/* package */OrgsModuleImpl(final ApiClientImpl client) {
		this.client = client;
	}

	@Override
	public ListenableFuture<ApiResponse<List<Org>>> getOrgs() {
		return client.getResponse(RequestParams.get("/orgs"),
				new TypeToken<List<Org>>() {
				}.getType());
	}

	@Override
	public ListenableFuture<ApiResponse<Success>> addAccounts(
			final AddAccounts message) {
		String endpoint = String.format("/orgs/%d/accounts/add",
				message.getId());
		FluentStringsMap params = new FluentStringsMap().add("ids",
				Util.joinList(message.getIds(), ","));
		return client.getResponse(RequestParams.post(endpoint, params),
				new TypeToken<Success>() {
				}.getType());
	}

}
