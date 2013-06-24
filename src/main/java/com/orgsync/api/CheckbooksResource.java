package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.checkbooks.Checkbook;
import com.orgsync.api.model.checkbooks.CheckbookEntry;

/**
 * <p>
 * Access to the checkbooks resource from the OrgSync API. This allows a client to get all checkbooks, create, update
 * and delete checkbooks. A client can also manage entries for a checkbook.
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/checkbooks">https://api.orgsync.com/api/docs/v2/checkbooks</a>
 * 
 * @author steffyj
 * 
 */
public interface CheckbooksResource {

    /**
     * Get all of the checkbooks.
     * 
     * @return a future to the response of all checkbooks
     */
    public ListenableFuture<ApiResponse<List<Checkbook>>>
            getCheckbooks();

    /**
     * Get a single checkbook.
     * 
     * @param checkbookId
     *            the id of the checkbook to get
     * @return a future to the response of the checkbook
     */
    public ListenableFuture<ApiResponse<Checkbook>>
            getCheckbook(int checkbookId);

    /**
     * Create a new checkbook for a given or with a given name.
     * 
     * @param orgId
     *            the org that owns this checkbook
     * @param name
     *            the name of the checkbook
     * @return a future to the response of the new checkbook
     */
    public ListenableFuture<ApiResponse<Checkbook>>
            createCheckbook(int orgId, String name);

    /**
     * Update the name of a given checkbook.
     * 
     * @param checkbookId
     *            the id of the checkbook
     * @param name
     *            the updated name
     * @return a future to the response of the checkbook
     */
    public ListenableFuture<ApiResponse<Checkbook>>
            updateCheckbook(int checkbookId, String name);

    /**
     * Delete the checkbook with the given id.
     * 
     * @param checkbookId
     *            the id of the checkbook to delete
     * @return a future to the response
     */
    public ListenableFuture<ApiResponse<Success>>
            deleteCheckbook(int checkbookId);

    /**
     * Get the checkbooks for a given org.
     * 
     * @param orgId
     *            the org to get checkbooks for
     * @return a future to the response to a list of checkbooks
     */
    public ListenableFuture<ApiResponse<List<Checkbook>>>
            getOrgCheckbooks(int orgId);

    /**
     * Get the checkbook entries for a given checkbook.
     * 
     * @param checkbookId
     *            the id of the checkbook
     * @return a future to the response of the checkbook entries
     */
    public ListenableFuture<ApiResponse<List<CheckbookEntry>>>
            getCheckbookEntries(int checkbookId);

    /**
     * Get a specific checkbook entry
     * 
     * @param checkbookEntryId
     *            the id of the checkbook entry
     * @return a future to the response of the checkbook entry
     */
    public ListenableFuture<ApiResponse<CheckbookEntry>>
            getCheckbookEntry(int checkbookEntryId);

    /**
     * Create a checkbook entry in the given checkbook with the amount and description.
     * 
     * @param checkbookId
     *            the id of the checkbook
     * @param amount
     *            the amount of the entry
     * @param description
     *            the description for this entry
     * @return a future to the response of the new checkbook entry
     */
    public ListenableFuture<ApiResponse<CheckbookEntry>>
            createCheckbookEntry(int checkbookId, String amount, String description);

    /**
     * Update a checkbook entry's amount and description
     * 
     * @param checkbookEntryId
     *            the id of the entry to update
     * @param amount
     *            the updated amount
     * @param description
     *            the updated description
     * @return the future to the response of the updated checkbook entry
     */
    public ListenableFuture<ApiResponse<CheckbookEntry>>
            updateCheckbookEntry(int checkbookEntryId, String amount, String description);

    /**
     * Delete a checkbook entry.
     * 
     * @param checkbookEntryId
     *            the id of the entry to delete
     * @return a future to the response
     */
    public ListenableFuture<ApiResponse<Success>>
            deleteCheckbookEntry(int checkbookEntryId);
}
