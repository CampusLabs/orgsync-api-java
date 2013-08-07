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

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgUpdateRequest;

/**
 * <p>
 * Access to the organizations resource from the OrgSync API. This allows a client to list and update orgs, as well as
 * add and remove accounts from an org.
 * 
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/orgs">https://api.orgsync.com/api/docs/v2/orgs</a>
 * 
 * 
 * @author steffyj
 * 
 */
public interface OrgsResource {

    /**
     * Get all of the orgs for this community.
     * 
     * @return a future to a resposne with all of the orgs
     */
    public ListenableFuture<ApiResponse<List<Org>>>
            getOrgs();

    /**
     * Get an org by its id.
     * 
     * @param orgId
     *            the id of the org to get
     * @return a future to the response with the org
     */
    public ListenableFuture<ApiResponse<Org>>
            getOrg(int orgId);

    /**
     * Update an org with the fields specified in the {@link OrgUpdateRequest}.
     * 
     * @param orgId
     *            the id of the org to update
     * @param request
     *            the fields to update
     * @return a future to a response with the updated orgs
     */
    public ListenableFuture<ApiResponse<Org>>
            updateOrg(int orgId, OrgUpdateRequest request);

    /**
     * List all of the accounts for an org based on id.
     * 
     * @param orgId
     *            the id of the org to list accounts for
     * @return a future to a resposne with the list of accounts
     */
    public ListenableFuture<ApiResponse<List<Account>>>
            listAccounts(int orgId);

    /**
     * Add a list of accounts to an org.
     * 
     * @param orgId
     *            the org to add the accounts to
     * @param accountIds
     *            the account ids to add to this org
     * @return a future to the response
     */
    public ListenableFuture<ApiResponse<Success>>
            addAccounts(int orgId, List<Integer> accountIds);

    /**
     * Remove a list of accounts from an org.
     * 
     * @param orgId
     *            the org to remove accounts for
     * @param accountIds
     *            the list of account ids to remove
     * @return a future to the response
     */
    public ListenableFuture<ApiResponse<Success>>
            removeAccounts(int orgId, List<Integer> accountIds);

}
