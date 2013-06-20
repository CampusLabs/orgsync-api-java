package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.groups.Group;

public interface GroupsResource {

    public ListenableFuture<ApiResponse<List<Group>>> getOrgGroups(int orgId);

    public ListenableFuture<ApiResponse<Group>> getGroup(int groupId);

    public ListenableFuture<ApiResponse<Group>> createGroup(String name, int orgId);

    public ListenableFuture<ApiResponse<Success>> deleteGroup(int groupId);

    public ListenableFuture<ApiResponse<Group>> updateGroup(int groupId, String name);

    public ListenableFuture<ApiResponse<List<Account>>> listAccounts(int groupId);

    public ListenableFuture<ApiResponse<Success>> addAccountsToGroup(int groupId, List<Integer> accountIds);

    public ListenableFuture<ApiResponse<Success>> removeAccountsToGroup(int groupId, List<Integer> accountIds);

}
