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

/*package*/class OrgsResourceImpl extends BaseResource implements OrgsResource {

    /* package */OrgsResourceImpl(final ApiClientImpl client) {
        super(client, "/orgs");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Org>>> getOrgs() {
        return list(Org.LIST_TYPE);
    }

    // @Override
    // public ListenableFuture<ApiResponse<Success>> addAccounts(
    // final AddAccounts accounts) {
    // String endpoint = String.format("/orgs/%d/accounts/add",
    // accounts.getId());
    // FluentStringsMap params = new FluentStringsMap().add("ids",
    // Util.joinList(accounts.getIds(), ","));
    // return getResponse(RequestParams.post(endpoint, params),
    // new TypeToken<Success>() {
    // }.getType());
    // }

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
