package com.orgsync.api.model.timesheets;

import java.util.Date;

import com.orgsync.api.model.IdAndName;

public class Timesheet {

    private int id;
    private Date startDate;
    private String alternateOrgName;
    private TimesheetAccount account;
    private IdAndName org;
    private IdAndName event;
    private String description;
    private int hours;
    private Date endDate;
    private String status;

    public final int getId() {
        return id;
    }

    public final Date getStartDate() {
        return startDate;
    }

    public final String getAlternateOrgName() {
        return alternateOrgName;
    }

    public final TimesheetAccount getAccount() {
        return account;
    }

    public final IdAndName getOrg() {
        return org;
    }

    public final IdAndName getEvent() {
        return event;
    }

    public final String getDescription() {
        return description;
    }

    public final int getHours() {
        return hours;
    }

    public final Date getEndDate() {
        return endDate;
    }

    public final String getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((alternateOrgName == null) ? 0 : alternateOrgName.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + hours;
        result = prime * result + id;
        result = prime * result + ((org == null) ? 0 : org.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Timesheet other = (Timesheet) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (alternateOrgName == null) {
            if (other.alternateOrgName != null)
                return false;
        } else if (!alternateOrgName.equals(other.alternateOrgName))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (event == null) {
            if (other.event != null)
                return false;
        } else if (!event.equals(other.event))
            return false;
        if (hours != other.hours)
            return false;
        if (id != other.id)
            return false;
        if (org == null) {
            if (other.org != null)
                return false;
        } else if (!org.equals(other.org))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Timesheet [id=" + id + ", startDate=" + startDate + ", alternateOrgName=" + alternateOrgName
                + ", account=" + account + ", org=" + org + ", event=" + event + ", description=" + description
                + ", hours=" + hours + ", endDate=" + endDate + ", status=" + status + "]";
    }

}