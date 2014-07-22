/*
 * Copyright 2014 OrgSync
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

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * A small snippet for an Organization.
 *
 * @author steffyj
 */
public class OrgSnippet {

    public static final Type TYPE = new TypeToken<OrgSnippet>() {
    }.getType();

    private int id;
    private int communityId;
    private String name;
    private String shortName;
    private String pictureUrl;

    public int getId() {
        return id;
    }

    public int getCommunityId() {
        return communityId;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrgSnippet)) return false;

        OrgSnippet that = (OrgSnippet) o;

        if (communityId != that.communityId) return false;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pictureUrl != null ? !pictureUrl.equals(that.pictureUrl) : that.pictureUrl != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + communityId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrgSnippet{");
        sb.append("id=").append(id);
        sb.append(", communityId=").append(communityId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", shortName='").append(shortName).append('\'');
        sb.append(", pictureUrl='").append(pictureUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
