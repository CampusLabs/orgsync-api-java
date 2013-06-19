package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.membership_logs.MembershipLogEntry;
import com.orgsync.api.model.membership_logs.MembershipLogEntryRequest;

public interface MembershipLogsResource {

    public ListenableFuture<ApiResponse<List<MembershipLogEntry>>>
            getLogEntries(MembershipLogEntryRequest request);

}
