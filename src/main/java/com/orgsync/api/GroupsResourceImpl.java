package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;
import static com.orgsync.api.Util.joinList;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.groups.Group;

/*package*/class GroupsResourceImpl extends BaseResource implements GroupsResource {

    private static final String ENDPOINT = "/groups";

    /* package */GroupsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Group>>> getOrgGroups(final int orgId) {
        String endpoint = String.format("/orgs/%d/groups", orgId);
        return getResponse(RequestParams.get(endpoint), new TypeToken<List<Group>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Group>> getGroup(final int groupId) {
        return getResponse(RequestParams.get(endpointForGroup(groupId)), new TypeToken<Group>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Group>> createGroup(final String name, final int orgId) {
        checkNotNull(name);

        FluentStringsMap params = new FluentStringsMap();
        params.add("name", name).add("org_id", String.valueOf(orgId));

        return getResponse(RequestParams.post(ENDPOINT, params), new TypeToken<Group>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> deleteGroup(final int groupId) {
        return getResponse(RequestParams.delete(endpointForGroup(groupId)), new TypeToken<Success>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Group>> updateGroup(final int groupId, final String name) {
        checkNotNull(name);

        FluentStringsMap params = new FluentStringsMap().add("name", name);
        return getResponse(RequestParams.put(endpointForGroup(groupId), params), new TypeToken<Group>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<Account>>> listAccounts(final int groupId) {
        return getResponse(RequestParams.get(endpointForGroup(groupId) + "/accounts"), new TypeToken<List<Account>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> addAccountsToGroup(final int groupId, final List<Integer> accountIds) {
        checkNotNull(accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", joinList(accountIds, ","));
        return getResponse(RequestParams.post(endpointForGroup(groupId) + "/accounts/add", params),
                new TypeToken<Success>() {
                }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> removeAccountsToGroup(final int groupId,
            final List<Integer> accountIds) {
        checkNotNull(accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", joinList(accountIds, ","));
        return getResponse(RequestParams.post(endpointForGroup(groupId) + "/accounts/remove", params),
                new TypeToken<Success>() {
                }.getType());
    }

    private static String endpointForGroup(final int groupId) {
        return ENDPOINT + "/" + groupId;
    }

}
