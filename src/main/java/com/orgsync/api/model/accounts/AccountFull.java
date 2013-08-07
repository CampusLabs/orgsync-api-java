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

    public final List<FormResponse> getProfileResponses() {
        return profileResponses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((profileResponses == null) ? 0 : profileResponses.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccountFull other = (AccountFull) obj;
        if (profileResponses == null) {
            if (other.profileResponses != null)
                return false;
        } else if (!profileResponses.equals(other.profileResponses))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AccountFull [profileResponses=" + profileResponses + "]";
    }

}
