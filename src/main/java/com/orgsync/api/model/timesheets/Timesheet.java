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
package com.orgsync.api.model.timesheets;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.IdAndName;

/**
 * A model for a timesheet.
 * 
 * @author steffyj
 * 
 */
public class Timesheet {

    public static final Type TYPE = new TypeToken<Timesheet>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<Timesheet>>() {
    }.getType();

    private int id;
    private String startDate;
    private String endDate;
    private String alternateOrgName;
    private TimesheetAccount account;
    private IdAndName org;
    private IdAndName event;
    private String description;
    private float hours;
    private String status;

    public final int getId() {
        return id;
    }

    public final String getStartDate() {
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

    public final float getHours() {
        return hours;
    }

    public final String getEndDate() {
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
        result = prime * result + Float.floatToIntBits(hours);
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
        if (Float.floatToIntBits(hours) != Float.floatToIntBits(other.hours))
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
        return "Timesheet [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", alternateOrgName="
                + alternateOrgName + ", account=" + account + ", org=" + org + ", event=" + event + ", description="
                + description + ", hours=" + hours + ", status=" + status + "]";
    }

}
