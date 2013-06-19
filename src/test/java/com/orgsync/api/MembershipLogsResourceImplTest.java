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
