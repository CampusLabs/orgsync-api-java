package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.orgs.AddAccounts;
import com.orgsync.api.model.orgs.Org;

public interface OrgsResource {

    public ListenableFuture<ApiResponse<List<Org>>> getOrgs();

    public ListenableFuture<ApiResponse<Success>> addAccounts(
            AddAccounts message);

    public ListenableFuture<ApiResponse<List<Account>>> listAccounts(
            int groupId);
}
