package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.accounts.Account;
import com.orgsync.api.model.classifications.Classification;

/**
 * <p>
 * Access to the classifications resource from the OrgSync API. This allows a client to get all classifications and do
 * basic CRUD operations. It also allows a user to list, add, and remove accounts from a classification.
 * <p>
 * See: <a
 * href="https://api.orgsync.com/api/docs/v2/classifications">https://api.orgsync.com/api/docs/v2/classifications</a>
 * 
 * @author steffyj
 * 
 */
public interface ClassificationsResource {

    /**
     * Get all of the classifications for this community.
     * 
     * @return a future to a response with all of the classifications
     */
    public ListenableFuture<ApiResponse<List<Classification>>>
            getClassifications();

    /**
     * Get the details of a single classification.
     * 
     * @param classificationId
     *            the id of the classification
     * @return a future to the response with the classification
     */
    public ListenableFuture<ApiResponse<Classification>>
            getClassification(int classificationId);

    /**
     * Delete a classification.
     * 
     * @param classificationId
     *            the id of the classification
     * @return a future to the response of success
     */
    public ListenableFuture<ApiResponse<Success>>
            deleteClassification(int classificationId);

    /**
     * Create a new classification.
     * 
     * @param name
     *            the name of the new classification
     * @return a future to the response with the new classification
     */
    public ListenableFuture<ApiResponse<Classification>>
            createClassification(String name);

    /**
     * Update a given classification.
     * 
     * @param classificationId
     *            the id of the classification to update
     * @param name
     *            the updated name for this classification
     * @return a future to a response with the updated classification
     */
    public ListenableFuture<ApiResponse<Classification>>
            updateClassification(int classificationId, String name);

    /**
     * List the accounts in the given classification.
     * 
     * @param classificationId
     *            the id of the classification
     * @return a future to a response with a list of accounts
     */
    public ListenableFuture<ApiResponse<List<Account>>>
            listAccounts(int classificationId);

    /**
     * Add a list of accounts to the given classification.
     * 
     * @param classificationId
     *            the id of the classification
     * @param accountIds
     *            the account ids to add
     * @return a future to the response of success
     */
    public ListenableFuture<ApiResponse<Success>>
            addAccountsToClassification(int classificationId, List<Integer> accountIds);

    /**
     * Remove a list of accounts from the given classification.
     * 
     * @param classificationId
     *            the id of the classification
     * @param accountIds
     *            the account ids to remove
     * @return a future to the response of success
     */
    public ListenableFuture<ApiResponse<Success>>
            removeAccountsFromClassification(int classificationId, List<Integer> accountIds);

}
