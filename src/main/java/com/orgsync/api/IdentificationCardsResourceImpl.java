package com.orgsync.api;

import java.util.List;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.identification_cards.IdentificationCard;

/**
 * The implementation of the identification cards resource.
 * 
 * @author steffyj
 * 
 */
/* package */class IdentificationCardsResourceImpl extends BaseResource implements IdentificationCardsResource {

    /* package */IdentificationCardsResourceImpl(final ApiClientImpl client) {
        super(client, "/identification_cards");
    }

    @Override
    public ListenableFuture<ApiResponse<List<IdentificationCard>>> getIdentificationCards() {
        return list(IdentificationCard.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> getIdentificationCard(final int cardId) {
        return show(cardId, IdentificationCard.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> getIdentificationCardByAccount(final int accountId) {
        String endpoint = String.format("%s/account_id/%d", getEndpoint(), accountId);
        return getResponse(RequestParams.get(endpoint), IdentificationCard.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> getIdentificationCardByCardNumber(final String cardNumber) {
        String endpoint = String.format("%s/number/%s", getEndpoint(), cardNumber);
        return getResponse(RequestParams.get(endpoint), IdentificationCard.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> addIdentificationCardToAccount(final int accountId,
            final String cardNumber) {
        FluentStringsMap params = new FluentStringsMap();
        params.add("account_id", String.valueOf(accountId)).add("number", cardNumber);

        return create(params, IdentificationCard.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> removeIdentificationCardFromAccount(final int cardId) {
        return delete(cardId, Success.TYPE);
    }

}
