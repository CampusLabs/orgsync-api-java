package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;

import java.util.List;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.checkbooks.Checkbook;
import com.orgsync.api.model.checkbooks.CheckbookEntry;

/**
 * Implementation of the Checkbooks resource.
 * 
 * @author steffyj
 * 
 */
/* package */class CheckbooksResourceImpl extends BaseResource implements CheckbooksResource {

    /* package */CheckbooksResourceImpl(final ApiClientImpl client) {
        super(client, "/checkbooks");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Checkbook>>> getCheckbooks() {
        return list(Checkbook.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Checkbook>> getCheckbook(final int checkbookId) {
        return show(checkbookId, Checkbook.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Checkbook>> createCheckbook(final int orgId, final String name) {
        checkNotNull(name);

        String endpoint = String.format("/orgs/%d/checkbooks", orgId);
        FluentStringsMap params = new FluentStringsMap().add("name", name);

        return getResponse(RequestParams.post(endpoint, params), Checkbook.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Checkbook>> updateCheckbook(final int checkbookId, final String name) {
        checkNotNull(name);

        FluentStringsMap params = new FluentStringsMap().add("name", name);

        return update(checkbookId, params, Checkbook.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> deleteCheckbook(final int checkbookId) {
        return delete(checkbookId, Success.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Checkbook>>> getOrgCheckbooks(final int orgId) {
        String endpoint = String.format("/orgs/%d%s", orgId, getEndpoint());
        return getResponse(RequestParams.get(endpoint), Checkbook.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<CheckbookEntry>>> getCheckbookEntries(final int checkbookId) {
        return getResponse(RequestParams.get(showFor(checkbookId) + "/entries"), CheckbookEntry.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<CheckbookEntry>> getCheckbookEntry(final int checkbookEntryId) {
        return getResponse(RequestParams.get("/checkbook_entries/" + checkbookEntryId), CheckbookEntry.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<CheckbookEntry>> createCheckbookEntry(final int checkbookId,
            final String amount,
            final String description) {
        checkNotNull(amount);
        checkNotNull(description);

        FluentStringsMap params = new FluentStringsMap().add("amount", amount).add("description", description);

        return getResponse(RequestParams.post(showFor(checkbookId) + "/entries", params), CheckbookEntry.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<CheckbookEntry>> updateCheckbookEntry(final int checkbookEntryId,
            final String amount,
            final String description) {
        checkNotNull(amount);
        checkNotNull(description);

        FluentStringsMap params = new FluentStringsMap().add("amount", amount).add("description", description);

        return getResponse(RequestParams.put("/checkbook_entries/" + checkbookEntryId, params), CheckbookEntry.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> deleteCheckbookEntry(final int checkbookEntryId) {
        return getResponse(RequestParams.delete("/checkbook_entries/" + checkbookEntryId), Success.TYPE);
    }

}
