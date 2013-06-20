package com.orgsync.api;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;

public class IdentificationCardsResourceImplTest extends BaseResourceTest {

    private final IdentificationCardsResourceImpl idCards = new IdentificationCardsResourceImpl(client);

    @Test
    public void testGetCards() throws Exception {
        idCards.getIdentificationCards();

        verifyRequest(RequestParams.get("/identification_cards"));
    }

    @Test
    public void testGetCardsById() throws Exception {
        idCards.getIdentificationCard(987);

        verifyRequest(RequestParams.get("/identification_cards/987"));
    }

    @Test
    public void testGetCardsByAccount() throws Exception {
        idCards.getIdentificationCardByAccount(876);

        verifyRequest(RequestParams.get("/identification_cards/account_id/876"));
    }

    @Test
    public void testGetCardsByCardNumber() throws Exception {
        String cardNumber = "abc123";
        idCards.getIdentificationCardByCardNumber(cardNumber);

        verifyRequest(RequestParams.get("/identification_cards/number/" + cardNumber));
    }

    @Test
    public void testAddCardToAccount() throws Exception {
        String cardNumber = "xyz321";
        idCards.addIdentificationCardToAccount(765, cardNumber);

        verifyRequest(RequestParams.post("/identification_cards",
                new FluentStringsMap().add("account_id", "765").add("number", cardNumber)));

    }

    @Test
    public void testRemoveCardFromAccount() throws Exception {
        idCards.removeIdentificationCardFromAccount(654);

        verifyRequest(RequestParams.delete("/identification_cards/654"));

    }
}
