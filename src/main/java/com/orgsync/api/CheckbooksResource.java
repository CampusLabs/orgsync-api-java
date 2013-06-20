package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.checkbooks.Checkbook;
import com.orgsync.api.model.checkbooks.CheckbookEntry;

public interface CheckbooksResource {

    public ListenableFuture<ApiResponse<List<Checkbook>>> getCheckbooks();

    public ListenableFuture<ApiResponse<Checkbook>> getCheckbook(int checkbookId);

    public ListenableFuture<ApiResponse<Checkbook>> createCheckbook(int orgId, String name);

    public ListenableFuture<ApiResponse<Checkbook>> updateCheckbook(int checkbookId, String name);

    public ListenableFuture<ApiResponse<Success>> deleteCheckbook(int checkbookId);

    public ListenableFuture<ApiResponse<List<Checkbook>>> getOrgCheckbooks(int orgId);

    public ListenableFuture<ApiResponse<List<CheckbookEntry>>> getCheckbookEntries(int checkbookId);

    public ListenableFuture<ApiResponse<CheckbookEntry>> getCheckbookEntry(int checkbookEntryId);

    public ListenableFuture<ApiResponse<CheckbookEntry>>
            createCheckbookEntry(int checkbookId, String amount, String description);

    public ListenableFuture<ApiResponse<CheckbookEntry>>
            updateCheckbookEntry(int checkbookEntryId, String amount, String description);

    public ListenableFuture<ApiResponse<Success>> deleteCheckbookEntry(int checkbookEntryId);
}
