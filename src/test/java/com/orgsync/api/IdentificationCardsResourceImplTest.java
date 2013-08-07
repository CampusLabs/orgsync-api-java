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
