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
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgUpdateRequest;

/**
 * The implementation of the orgs endpoint.
 * 
 * @author steffyj
 * 
 */
/* package */class OrgsResourceImpl extends BaseResource implements OrgsResource {

    /* package */OrgsResourceImpl(final ApiClientImpl client) {
        super(client, "/orgs");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Org>>> getOrgs() {
        return list(Org.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Org>> getOrg(final int orgId) {
        return show(orgId, Org.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Org>> updateOrg(final int orgId, final OrgUpdateRequest request) {
        checkNotNull(request);

        FluentStringsMap params = new FluentStringsMap();

        checkAddField(params, "alternate_id", request.getAlternateId());
        checkAddField(params, "short_name", request.getShortName());
        checkAddField(params, "is_disabled", request.getIsDisabled());

        return update(orgId, params, Org.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Account>>> listAccounts(final int orgId) {
        String endpoint = showFor(orgId) + "/accounts";
        return getResponse(RequestParams.get(endpoint), Account.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> addAccounts(final int orgId, final List<Integer> accountIds) {
        checkNotNull(accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", joinList(accountIds, ","));
        return getResponse(RequestParams.post(showFor(orgId) + "/accounts/add", params), Success.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> removeAccounts(final int orgId, final List<Integer> accountIds) {
        checkNotNull(accountIds);

        FluentStringsMap params = new FluentStringsMap().add("ids", joinList(accountIds, ","));
        return getResponse(RequestParams.post(showFor(orgId) + "/accounts/remove", params), Success.TYPE);
    }

}
