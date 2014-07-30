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
    public ListenableFuture<ApiResponse<Checkbook>> createCheckbook(final int orgId, final String name, final boolean isReadOnly) {
        checkNotNull(name);

        String endpoint = listFor("/orgs/%d", orgId);
        FluentStringsMap params = new FluentStringsMap()
                .add("name", name)
                .add("is_read_only", String.valueOf(isReadOnly));

        return getResponse(RequestParams.post(endpoint, params), Checkbook.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Checkbook>> updateCheckbook(final int checkbookId, final String name, final boolean isReadOnly) {
        checkNotNull(name);

        FluentStringsMap params = new FluentStringsMap()
                .add("name", name)
                .add("is_read_only", String.valueOf(isReadOnly));

        return update(checkbookId, params, Checkbook.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> deleteCheckbook(final int checkbookId) {
        return delete(checkbookId, Success.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Checkbook>>> getOrgCheckbooks(final int orgId) {
        String endpoint = listFor("/orgs/%d", orgId);
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
