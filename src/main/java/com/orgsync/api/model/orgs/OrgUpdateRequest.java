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
package com.orgsync.api.model.orgs;

import com.orgsync.api.model.accounts.AccountUpdateRequest;
import com.orgsync.api.model.forms.FormUpdate;

import java.util.LinkedList;
import java.util.List;

/**
 * A request to update an org.
 * 
 * @author steffyj
 * 
 */
public class OrgUpdateRequest {

    private Boolean isDisabled;
    private String alternateId;
    private String shortName;
    private List<FormUpdate> profileResponses = new LinkedList<FormUpdate>();

    public final Boolean getIsDisabled() {
        return isDisabled;
    }

    public final OrgUpdateRequest setIsDisabled(final Boolean isDisabled) {
        this.isDisabled = isDisabled;
        return this;
    }

    public final String getAlternateId() {
        return alternateId;
    }

    public final OrgUpdateRequest setAlternateId(final String alternateId) {
        this.alternateId = alternateId;
        return this;
    }

    public final String getShortName() {
        return shortName;
    }

    public final OrgUpdateRequest setShortName(final String shortName) {
        this.shortName = shortName;
        return this;
    }

    public List<FormUpdate> getProfileResponses() {
        return profileResponses;
    }

    public OrgUpdateRequest addProfileUpdate(FormUpdate update) {
        profileResponses.add(update);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrgUpdateRequest)) return false;

        OrgUpdateRequest that = (OrgUpdateRequest) o;

        if (alternateId != null ? !alternateId.equals(that.alternateId) : that.alternateId != null) return false;
        if (isDisabled != null ? !isDisabled.equals(that.isDisabled) : that.isDisabled != null) return false;
        if (profileResponses != null ? !profileResponses.equals(that.profileResponses) : that.profileResponses != null)
            return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isDisabled != null ? isDisabled.hashCode() : 0;
        result = 31 * result + (alternateId != null ? alternateId.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (profileResponses != null ? profileResponses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrgUpdateRequest{");
        sb.append("isDisabled=").append(isDisabled);
        sb.append(", alternateId='").append(alternateId).append('\'');
        sb.append(", shortName='").append(shortName).append('\'');
        sb.append(", profileResponses=").append(profileResponses);
        sb.append('}');
        return sb.toString();
    }
}
