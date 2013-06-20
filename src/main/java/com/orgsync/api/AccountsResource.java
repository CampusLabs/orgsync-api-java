package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.accounts.AccountDetail;
import com.orgsync.api.model.accounts.AccountFull;
import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.accounts.CustomProfileField;

public interface AccountsResource {

    public ListenableFuture<ApiResponse<List<Account>>> getAccounts();

    public ListenableFuture<ApiResponse<AccountFull>> getAccount(int accountId);

    public ListenableFuture<ApiResponse<AccountDetail>> getAccountByEmail(String email);

    public ListenableFuture<ApiResponse<AccountDetail>> getAccountByUsername(String username);

    public ListenableFuture<ApiResponse<AccountDetail>> getAccountByCustomProfile(String keyword);

    public ListenableFuture<ApiResponse<List<CustomProfileField>>> getCustomProfileFields();

    public ListenableFuture<ApiResponse<AccountFull>> updateAccount(int accountId, AccountUpdateRequest request);

}
