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

import java.util.List;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountDetail;
import com.orgsync.api.model.accounts.AccountFull;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.accounts.CustomProfileField;

/**
 * The implementation of the accounts resource.
 * 
 * @author steffyj
 * 
 */
/* package */class AccountsResourceImpl extends BaseResource implements AccountsResource {

    /* package */AccountsResourceImpl(final ApiClientImpl client) {
        super(client, "/accounts");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Account>>> getAccounts() {
        return list(Account.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<AccountFull>> getAccount(final int accountId) {
        return show(accountId, AccountFull.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<AccountDetail>> getAccountByEmail(final String email) {
        checkNotNull(email);
        return getResponse(RequestParams.get(getEndpoint() + "/email/" + email), AccountDetail.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<AccountDetail>> getAccountByUsername(final String username) {
        checkNotNull(username);
        return getResponse(RequestParams.get(getEndpoint() + "/username/" + username), AccountDetail.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<AccountDetail>> getAccountByCustomProfile(final String keyword) {
        checkNotNull(keyword);
        return getResponse(RequestParams.get(getEndpoint() + "/custom_profile/" + keyword), AccountDetail.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<CustomProfileField>>> getCustomProfileFields() {
        return getResponse(RequestParams.get(getEndpoint() + "/profile_fields"), CustomProfileField.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<AccountFull>> updateAccount(final int accountId,
            final AccountUpdateRequest request) {
        checkNotNull(request);

        return update(accountId, toParams(request), AccountFull.TYPE);
    }

    /**
     * Convert the account update request into a query param map.
     * 
     * @param request
     *            the update request
     * @return the map with query params
     */
    private FluentStringsMap toParams(final AccountUpdateRequest request) {
        FluentStringsMap params = new FluentStringsMap();

        checkAddField(params, "username", request.getUsername());
        checkAddField(params, "first_name", request.getFirstName());
        checkAddField(params, "last_name", request.getLastName());
        checkAddField(params, "middle_initial", request.getMiddleInitial());
        checkAddField(params, "email", request.getEmail());
        checkAddField(params, "phone_number", request.getPhoneNumber());
        checkAddField(params, "address", request.getAddress());
        checkAddField(params, "city", request.getCity());
        checkAddField(params, "state", request.getState());
        checkAddField(params, "zip", request.getZip());

        if (request.getElement() != null) {
            params.add(
                    String.format("profile_entries[%d]", request.getElement().getElementId()),
                    String.valueOf(request.getElement().getElementValue()));
        }

        return params;
    }

}
