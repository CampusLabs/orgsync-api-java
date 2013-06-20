package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.identification_cards.IdentificationCard;

public interface IdentificationCardsResource {

    public ListenableFuture<ApiResponse<List<IdentificationCard>>>
            getIdentificationCards();

    public ListenableFuture<ApiResponse<IdentificationCard>>
            getIdentificationCard(int cardId);

    public ListenableFuture<ApiResponse<List<IdentificationCard>>>
            getIdentificationCardByAccount(int accountId);

    public ListenableFuture<ApiResponse<IdentificationCard>>
            getIdentificationCardByCardNumber(String cardNumber);

    public ListenableFuture<ApiResponse<IdentificationCard>>
            addIdentificationCardToAccount(int accountId, String cardNumber);

    public ListenableFuture<ApiResponse<Success>>
            removeIdentificationCardFromAccount(int cardId);
}
