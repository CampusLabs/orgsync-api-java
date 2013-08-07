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
package com.orgsync.api.model.courses;

/**
 * A request to update or create a {@link Course}.
 * 
 * @author steffyj
 * 
 */
public class CourseUpdateRequest {

    public static final int NO_ACCOUNT_ID = -1;

    private String name;
    private int accountId = NO_ACCOUNT_ID;
    private String link;
    private String date;
    private String section;

    public final String getName() {
        return name;
    }

    public final CourseUpdateRequest setName(final String name) {
        this.name = name;
        return this;
    }

    public final int getAccountId() {
        return accountId;
    }

    public final CourseUpdateRequest setAccountId(final int accountId) {
        this.accountId = accountId;
        return this;
    }

    public final String getLink() {
        return link;
    }

    public final CourseUpdateRequest setLink(final String link) {
        this.link = link;
        return this;
    }

    public final String getDate() {
        return date;
    }

    public final CourseUpdateRequest setDate(final String date) {
        this.date = date;
        return this;
    }

    public final String getSection() {
        return section;
    }

    public final CourseUpdateRequest setSection(final String section) {
        this.section = section;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountId;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
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
        CourseUpdateRequest other = (CourseUpdateRequest) obj;
        if (accountId != other.accountId)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (section == null) {
            if (other.section != null)
                return false;
        } else if (!section.equals(other.section))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CourseUpdateRequest [name=" + name + ", accountId=" + accountId + ", link=" + link + ", date=" + date
                + ", section=" + section + "]";
    }

}
