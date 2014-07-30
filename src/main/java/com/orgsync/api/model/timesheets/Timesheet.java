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
    private String attendanceStatus;

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

    public final String getAttendanceStatus() {
        return attendanceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timesheet)) return false;

        Timesheet timesheet = (Timesheet) o;

        if (Float.compare(timesheet.hours, hours) != 0) return false;
        if (id != timesheet.id) return false;
        if (account != null ? !account.equals(timesheet.account) : timesheet.account != null) return false;
        if (alternateOrgName != null ? !alternateOrgName.equals(timesheet.alternateOrgName) : timesheet.alternateOrgName != null)
            return false;
        if (attendanceStatus != null ? !attendanceStatus.equals(timesheet.attendanceStatus) : timesheet.attendanceStatus != null)
            return false;
        if (description != null ? !description.equals(timesheet.description) : timesheet.description != null)
            return false;
        if (endDate != null ? !endDate.equals(timesheet.endDate) : timesheet.endDate != null) return false;
        if (event != null ? !event.equals(timesheet.event) : timesheet.event != null) return false;
        if (org != null ? !org.equals(timesheet.org) : timesheet.org != null) return false;
        if (startDate != null ? !startDate.equals(timesheet.startDate) : timesheet.startDate != null) return false;
        if (status != null ? !status.equals(timesheet.status) : timesheet.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (alternateOrgName != null ? alternateOrgName.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (org != null ? org.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (hours != +0.0f ? Float.floatToIntBits(hours) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (attendanceStatus != null ? attendanceStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Timesheet{");
        sb.append("id=").append(id);
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", alternateOrgName='").append(alternateOrgName).append('\'');
        sb.append(", account=").append(account);
        sb.append(", org=").append(org);
        sb.append(", event=").append(event);
        sb.append(", description='").append(description).append('\'');
        sb.append(", hours=").append(hours);
        sb.append(", status='").append(status).append('\'');
        sb.append(", attendanceStatus='").append(attendanceStatus).append('\'');
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
