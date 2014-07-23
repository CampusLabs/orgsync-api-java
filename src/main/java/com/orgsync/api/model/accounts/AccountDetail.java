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

/**
 * Detailed view of an account.
 * 
 * @author steffyj
 * 
 */
public class AccountDetail extends Account {

    public static final Type TYPE = new TypeToken<AccountDetail>() {
    }.getType();

    private String picUrl;
    private List<Integer> orgIds;

    public final String getPicUrl() {
        return picUrl;
    }

    public final List<Integer> getOrgIds() {
        return orgIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((orgIds == null) ? 0 : orgIds.hashCode());
        result = prime * result + ((picUrl == null) ? 0 : picUrl.hashCode());
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
        AccountDetail other = (AccountDetail) obj;
        if (orgIds == null) {
            if (other.orgIds != null)
                return false;
        } else if (!orgIds.equals(other.orgIds))
            return false;
        if (picUrl == null) {
            if (other.picUrl != null)
                return false;
        } else if (!picUrl.equals(other.picUrl))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountDetail{");
        sb.append("picUrl='").append(picUrl).append('\'');
        sb.append(", orgIds=").append(orgIds);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
