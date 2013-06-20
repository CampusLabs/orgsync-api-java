package com.orgsync.api;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.identification_cards.IdentificationCard;

/*package*/class IdentificationCardsResourceImpl extends BaseResource implements IdentificationCardsResource {

    private static final String ENDPOINT = "/identification_cards";

    /* package */IdentificationCardsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<List<IdentificationCard>>> getIdentificationCards() {
        return getResponse(RequestParams.get(ENDPOINT), new TypeToken<List<IdentificationCard>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> getIdentificationCard(final int cardId) {
        return getResponse(RequestParams.get(ENDPOINT + "/" + cardId), new TypeToken<IdentificationCard>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<IdentificationCard>>> getIdentificationCardByAccount(final int accountId) {
        String endpoint = String.format("%s/account_id/%d", ENDPOINT, accountId);
        return getResponse(RequestParams.get(endpoint), new TypeToken<IdentificationCard>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> getIdentificationCardByCardNumber(final String cardNumber) {
        String endpoint = String.format("%s/number/%s", ENDPOINT, cardNumber);
        return getResponse(RequestParams.get(endpoint), new TypeToken<IdentificationCard>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<IdentificationCard>> addIdentificationCardToAccount(final int accountId,
            final String cardNumber) {
        FluentStringsMap params = new FluentStringsMap();
        params.add("account_id", String.valueOf(accountId)).add("number", cardNumber);

        return getResponse(RequestParams.post(ENDPOINT, params), new TypeToken<IdentificationCard>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> removeIdentificationCardFromAccount(final int cardId) {
        String endpoint = String.format("%s/%d", ENDPOINT, cardId);
        return getResponse(RequestParams.delete(endpoint), new TypeToken<Success>() {
        }.getType());
    }

}
