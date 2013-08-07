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
import com.orgsync.api.model.membership_logs.MembershipLogEntryRequest;

public class MembershipLogsResourceImplTest extends BaseResourceTest {

    private final MembershipLogsResourceImpl logs = new MembershipLogsResourceImpl(client);

    @Test
    public void getGetLogEntries() throws Exception {
        MembershipLogEntryRequest request = new MembershipLogEntryRequest();
        request.setAccountId(234);
        request.setOrgId(456);
        logs.getLogEntries(request);

        FluentStringsMap queryParams = new FluentStringsMap();
        queryParams.add("org_id", "456").add("account_id", "234");
        verifyRequest(RequestParams.get("/org_membership_log_entries", queryParams));
    }
}
