package com.orgsync.api;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.orgs.AddAccounts;
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgAccount;

/*package*/class OrgsResourceImpl extends BaseResource implements OrgsResource {

    /* package */OrgsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Org>>> getOrgs() {
        return getResponse(RequestParams.get("/orgs"),
                new TypeToken<List<Org>>() {
                }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> addAccounts(
            final AddAccounts accounts) {
        String endpoint = String.format("/orgs/%d/accounts/add",
                accounts.getId());
        FluentStringsMap params = new FluentStringsMap().add("ids",
                Util.joinList(accounts.getIds(), ","));
        return getResponse(RequestParams.post(endpoint, params),
                new TypeToken<Success>() {
                }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<OrgAccount>>> listAccounts(
            final int groupId) {
        String endpoint = String.format("/orgs/%d/accounts", groupId);
        return getResponse(RequestParams.get(endpoint),
                new TypeToken<List<OrgAccount>>() {
                }.getType());
    }

}
