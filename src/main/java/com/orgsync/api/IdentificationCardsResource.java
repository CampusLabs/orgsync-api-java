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
import com.orgsync.api.model.identification_cards.IdentificationCard;

/**
 * <p>
 * Access to the identification cards resource from the OrgSync API. This allows a client to get identifications cards
 * using a few search critera, as well as add and remove an identification card from an account.
 * 
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/identification_cards">https://api.orgsync.com/api/docs/v2/
 * identification_cards</a>
 * 
 * 
 * @author steffyj
 * 
 */
public interface IdentificationCardsResource {

    /**
     * Get all of the identification cards for this community.
     * 
     * @return a future to the response with all the identification cards
     */
    public ListenableFuture<ApiResponse<List<IdentificationCard>>>
            getIdentificationCards();

    /**
     * Get a single identification card by its id.
     * 
     * @param cardId
     *            the id of the identification card to get
     * @return a future to the response with the identification card
     */
    public ListenableFuture<ApiResponse<IdentificationCard>>
            getIdentificationCard(int cardId);

    /**
     * Get an idenfitication card for a given account.
     * 
     * @param accountId
     *            the account id to get the identification card for
     * @return a future to the response with the identification card
     */
    public ListenableFuture<ApiResponse<IdentificationCard>>
            getIdentificationCardByAccount(int accountId);

    /**
     * Get an identification card by its card number.
     * 
     * @param cardNumber
     *            the card number to get the identification card for
     * @return a future to a response with an identification card
     */
    public ListenableFuture<ApiResponse<IdentificationCard>>
            getIdentificationCardByCardNumber(String cardNumber);

    /**
     * Add an identification card to an account by its card number.
     * 
     * @param accountId
     *            the account id to add the card to
     * @param cardNumber
     *            the card number of the card to add
     * @return a future to the response with the added identification card
     */
    public ListenableFuture<ApiResponse<IdentificationCard>>
            addIdentificationCardToAccount(int accountId, String cardNumber);

    /**
     * Remove an identification card from an account by its card id.
     * 
     * @param cardId
     *            the id of the card to remove
     * @return a future to a response
     */
    public ListenableFuture<ApiResponse<Success>>
            removeIdentificationCardFromAccount(int cardId);
}
