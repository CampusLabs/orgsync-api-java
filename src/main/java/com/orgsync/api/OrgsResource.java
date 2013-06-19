package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.orgs.AddAccounts;
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgAccount;

public interface OrgsResource {

    public ListenableFuture<ApiResponse<List<Org>>> getOrgs();

    public ListenableFuture<ApiResponse<Success>> addAccounts(
            AddAccounts message);

    public ListenableFuture<ApiResponse<List<OrgAccount>>> listAccounts(
            int groupId);
}
