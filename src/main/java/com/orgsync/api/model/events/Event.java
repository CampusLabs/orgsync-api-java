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
package com.orgsync.api.model.events;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.IdAndName;

/**
 * A model for an event.
 * 
 * @author steffyj
 * 
 */
public class Event {

    public static final Type LIST_TYPE = new TypeToken<List<Event>>() {
    }.getType();

    private int id;
    private String name;
    private String description;
    private boolean isPublic;
    private boolean isApproved;
    private IdAndName category;
    private IdAndName umbrellaCategory;
    private String location;
    private String htmlDescription;
    private int rsvps;
    private int orgId;
    private List<EventOccurrence> occurrences;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean isPublic() {
        return isPublic;
    }

    public final boolean isApproved() {
        return isApproved;
    }

    public final IdAndName getCategory() {
        return category;
    }

    public final IdAndName getUmbrellaCategory() {
        return umbrellaCategory;
    }

    public final String getLocation() {
        return location;
    }

    public final String getHtmlDescription() {
        return htmlDescription;
    }

    public final int getRsvps() {
        return rsvps;
    }

    public final int getOrgId() {
        return orgId;
    }

    public final List<EventOccurrence> getOccurrences() {
        return occurrences;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((htmlDescription == null) ? 0 : htmlDescription.hashCode());
        result = prime * result + id;
        result = prime * result + (isApproved ? 1231 : 1237);
        result = prime * result + (isPublic ? 1231 : 1237);
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((occurrences == null) ? 0 : occurrences.hashCode());
        result = prime * result + orgId;
        result = prime * result + rsvps;
        result = prime * result + ((umbrellaCategory == null) ? 0 : umbrellaCategory.hashCode());
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
        Event other = (Event) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (htmlDescription == null) {
            if (other.htmlDescription != null)
                return false;
        } else if (!htmlDescription.equals(other.htmlDescription))
            return false;
        if (id != other.id)
            return false;
        if (isApproved != other.isApproved)
            return false;
        if (isPublic != other.isPublic)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (occurrences == null) {
            if (other.occurrences != null)
                return false;
        } else if (!occurrences.equals(other.occurrences))
            return false;
        if (orgId != other.orgId)
            return false;
        if (rsvps != other.rsvps)
            return false;
        if (umbrellaCategory == null) {
            if (other.umbrellaCategory != null)
                return false;
        } else if (!umbrellaCategory.equals(other.umbrellaCategory))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", description=" + description + ", isPublic=" + isPublic
                + ", isApproved=" + isApproved + ", category=" + category + ", umbrellaCategory=" + umbrellaCategory
                + ", location=" + location + ", htmlDescription=" + htmlDescription + ", rsvps=" + rsvps + ", orgId="
                + orgId + ", occurrences=" + occurrences + "]";
    }

}
