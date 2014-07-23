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

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountCreateRequest;
import com.orgsync.api.model.accounts.AccountDetail;
import com.orgsync.api.model.accounts.AccountFull;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.accounts.CustomProfileField;

import java.util.List;

/**
 * <p>
 * Access to the accounts resource from the OrgSync API. This allows a client to get all accounts, get an account by
 * account ID, or search based on other fields. A client can also update an account using
 * {@link #updateAccount(int, AccountUpdateRequest)} with an {@link AccountUpdateRequest}. Finally, access to the custom
 * profile is provided by {@link #getCustomProfileFields()}.
 * 
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/accounts">https://api.orgsync.com/api/docs/v2/accounts</a>
 * 
 * @author steffyj
 * 
 */
public interface AccountsResource {

    /**
     * Get all of the accounts in this community.
     * 
     * @return a future for the response with a list of accounts
     */
    public ListenableFuture<ApiResponse<List<Account>>>
            getAccounts();

    /**
     * Get an account description for a given account id.
     * 
     * @param accountId
     *            the id of the account to retrieve
     * @return a future to the response with the full account details
     */
    public ListenableFuture<ApiResponse<AccountFull>>
            getAccount(int accountId);

    /**
     * Find an account by email address.
     * 
     * @param email
     *            the email address of the account to find
     * @return a future to the response with the account details.
     */
    public ListenableFuture<ApiResponse<AccountDetail>>
            getAccountByEmail(String email);

    /**
     * Find an account by username.
     * 
     * @param username
     *            the username of the account to find
     * @return a future to the response with the account details
     */
    public ListenableFuture<ApiResponse<AccountDetail>>
            getAccountByUsername(String username);

    /**
     * Find an account with a custom profile keyword.
     * 
     * @param keyword
     *            the custom profile keyword
     * @return a future to the response with the account details
     */
    public ListenableFuture<ApiResponse<AccountDetail>>
            getAccountByCustomProfile(String keyword);

    /**
     * Search for an account by response to a custom profile question.
     *
     *
     * @param questionId
     *              The ID of the custom profile question to search
     * @param responseQuery
     *              The query for a matching response
     * @return a future to the response with a list of account details
     */
    public ListenableFuture<ApiResponse<List<AccountDetail>>>
            getAccountsByCustomProfileResponse(int questionId, String responseQuery);

    /**
     * Get the list of custom profile fields for an account.
     * 
     * @return a future to the response of the custom profile fields
     */
    public ListenableFuture<ApiResponse<List<CustomProfileField>>>
            getCustomProfileFields();

    /**
     * Create an account with the given attributes.
     *
     * @param request
     *              The request to make the account
     * @return a future to a response with the created account information
     */
    public ListenableFuture<ApiResponse<AccountFull>>
            createAccount(AccountCreateRequest request);

    /**
     * Update an account with the given {@link AccountUpdateRequest}.
     * 
     * @param accountId
     *            the account to update
     * @param request
     *            the requested fields to update
     * @return a future to a response with the updated account information
     */
    public ListenableFuture<ApiResponse<AccountFull>>
            updateAccount(int accountId, AccountUpdateRequest request);

}
