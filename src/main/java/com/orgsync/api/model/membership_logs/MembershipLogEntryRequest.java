package com.orgsync.api.model.membership_logs;

import java.util.Date;

public class MembershipLogEntryRequest {

    public static final int NO_ACCOUNT_ID = -1;
    public static final int NO_ORG_ID = -1;

    private Date startDate;
    private Date endDate;
    private int accountId = NO_ACCOUNT_ID;
    private int orgId = NO_ORG_ID;

    public final Date getStartDate() {
        return startDate;
    }

    public final MembershipLogEntryRequest setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public final Date getEndDate() {
        return endDate;
    }

    public final MembershipLogEntryRequest setEndDate(final Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public final int getAccountId() {
        return accountId;
    }

    public final MembershipLogEntryRequest setAccountId(final int accountId) {
        this.accountId = accountId;
        return this;
    }

    public final int getOrgId() {
        return orgId;
    }

    public final MembershipLogEntryRequest setOrgId(final int orgId) {
        this.orgId = orgId;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountId;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + orgId;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MembershipLogEntryRequest other = (MembershipLogEntryRequest) obj;
        if (accountId != other.accountId)
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (orgId != other.orgId)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MembershipLogEntryRequest [startDate=" + startDate + ", endDate=" + endDate + ", accountId="
                + accountId + ", orgId=" + orgId + "]";
    }

}
