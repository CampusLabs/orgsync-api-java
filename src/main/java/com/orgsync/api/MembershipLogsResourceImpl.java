package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.membership_logs.MembershipLogEntry;
import com.orgsync.api.model.membership_logs.MembershipLogEntryRequest;

/*package*/class MembershipLogsResourceImpl extends BaseResource implements MembershipLogsResource {

    /* package */MembershipLogsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<List<MembershipLogEntry>>> getLogEntries(final MembershipLogEntryRequest request) {
        checkNotNull(request);

        return getResponse(RequestParams.get("/org_membership_log_entries", toParams(request)),
                new TypeToken<List<MembershipLogEntry>>() {
                }.getType());
    }

    private FluentStringsMap toParams(final MembershipLogEntryRequest request) {
        FluentStringsMap map = new FluentStringsMap();

        if (request.getOrgId() != MembershipLogEntryRequest.NO_ORG_ID) {
            map.add("org_id", String.valueOf(request.getOrgId()));
        }

        if (request.getAccountId() != MembershipLogEntryRequest.NO_ACCOUNT_ID) {
            map.add("account_id", String.valueOf(request.getAccountId()));
        }

        if (request.getStartDate() != null) {
            map.add("start_date", dateToQueryParam(request.getStartDate()));
        }

        if (request.getEndDate() != null) {
            map.add("end_date", dateToQueryParam(request.getEndDate()));
        }

        return map;
    }
}
