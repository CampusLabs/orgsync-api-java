package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.messages.Success;
import com.orgsync.api.messages.orgs.AddAccounts;
import com.orgsync.api.messages.orgs.Org;
import com.orgsync.api.messages.orgs.OrgAccount;

public interface OrgsResource {

    public ListenableFuture<ApiResponse<List<Org>>> getOrgs();

    public ListenableFuture<ApiResponse<Success>> addAccounts(
            AddAccounts message);

    public ListenableFuture<ApiResponse<List<OrgAccount>>> listAccounts(
            int groupId);
}