/*
 * Copyright 2014 OrgSync
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

import com.ning.http.client.FluentStringsMap;
import org.junit.Test;

/**
 * @author steffyj
 */
public class ExportsResourceImplTest extends BaseResourceTest {

    private final ExportsResourceImpl exports = new ExportsResourceImpl(client);

    @Test
    public void testRequestToken() {
        String exportType = "abc";
        exports.requestToken(exportType);

        verifyRequest(RequestParams.get("/exports/" + exportType));
    }

    @Test
    public void testRedeemToken() {
        String token = "something";
        exports.redeemToken(token);

        verifyRequest(RequestParams.get("/exports/redeem", new FluentStringsMap().add("export_token", token)));
    }
}
