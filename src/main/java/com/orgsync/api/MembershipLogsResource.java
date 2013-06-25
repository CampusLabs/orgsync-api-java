package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.membership_logs.MembershipLogEntry;
import com.orgsync.api.model.membership_logs.MembershipLogEntryRequest;

/**
 * <p>
 * Access to the membership log entries resource from the OrgSync API. This allows a client to query the membership log
 * entries based on the critera available in {@link MembershipLogEntryRequest}.
 * 
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/org_membership_log_entries">https://api.orgsync.com/api/docs/v2/
 * org_membership_log_entries</a>
 * 
 * @author steffyj
 * 
 */
public interface MembershipLogsResource {

    /**
     * Get the membership log entries that match the criteria specified in the request.
     * 
     * @param request
     *            the criteria to query
     * @return a future to the list of log entries
     */
    public ListenableFuture<ApiResponse<List<MembershipLogEntry>>>
            getLogEntries(MembershipLogEntryRequest request);

}
