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
package com.orgsync.api.model.accounts;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.forms.FormResponse;

/**
 * Full information about an account.
 * 
 * @author steffyj
 * 
 */
public class AccountFull extends AccountDetail {

    public static final Type TYPE = new TypeToken<AccountFull>() {
    }.getType();

    private List<FormResponse> profileResponses;

    private String aboutMe;

    public final List<FormResponse> getProfileResponses() {
        return profileResponses;
    }

    public final String getAboutMe() {
        return aboutMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountFull)) return false;
        if (!super.equals(o)) return false;

        AccountFull that = (AccountFull) o;

        if (aboutMe != null ? !aboutMe.equals(that.aboutMe) : that.aboutMe != null) return false;
        if (profileResponses != null ? !profileResponses.equals(that.profileResponses) : that.profileResponses != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (profileResponses != null ? profileResponses.hashCode() : 0);
        result = 31 * result + (aboutMe != null ? aboutMe.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountFull{");
        sb.append("profileResponses=").append(profileResponses);
        sb.append(", aboutMe='").append(aboutMe).append('\'');
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
