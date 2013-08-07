/*
 * Copyright 2013 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;
import static com.orgsync.api.Util.joinList;

import java.util.List;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.groups.Group;

/**
 * The groups resource implementation.
 * 
 * @author steffyj
 * 
 */
/* package */class GroupsResourceImpl extends BaseResource implements GroupsResource {

    /* package */GroupsResourceImpl(final ApiClientImpl client) {
        super(client, "/groups");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Group>>> getOrgGroups(final int orgId) {
        String prefix = String.format("/orgs/%d", orgId);
        return list(prefix, Group.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Group>> getGroup(final int groupId) {
        return show(groupId, Group.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Group>> createGroup(final String name, final int orgId) {
        checkNotNull(name);

        FluentStringsMap params = new FluentStringsMap();
        params.add("name", name).add("org_id", String.valueOf(orgId));

        return create(params, Group.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> deleteGroup(final int groupId) {
        return delete(groupId, Success.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Group>> updateGroup(final int groupId, final String name) {
        checkNotNull(name);

        FluentStringsMap params = new FluentStringsMap().add("name", name);
        return update(groupId, params, Group.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Account>>> listAccounts(final int groupId) {
        return getResponse(RequestParams.get(showFor(groupId) + "/accounts"), Account.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> addAccountsToGroup(final int groupId, final List<Integer> accountIds) {
        checkNotNull(accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", joinList(accountIds, ","));
        return getResponse(RequestParams.post(showFor(groupId) + "/accounts/add", params), Success.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> removeAccountsToGroup(final int groupId,
            final List<Integer> accountIds) {
        checkNotNull(accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", joinList(accountIds, ","));
        return getResponse(RequestParams.post(showFor(groupId) + "/accounts/remove", params), Success.TYPE);
    }
}
