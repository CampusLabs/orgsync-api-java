package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgUpdateRequest;

public interface OrgsResource {

    public ListenableFuture<ApiResponse<List<Org>>> getOrgs();

    public ListenableFuture<ApiResponse<Org>> getOrg(int orgId);

    public ListenableFuture<ApiResponse<Org>> updateOrg(int orgId, OrgUpdateRequest request);

    public ListenableFuture<ApiResponse<List<Account>>> listAccounts(int orgId);

    public ListenableFuture<ApiResponse<Success>> addAccounts(int orgId, List<Integer> accountIds);

    public ListenableFuture<ApiResponse<Success>> removeAccounts(int orgId, List<Integer> accountIds);

}
