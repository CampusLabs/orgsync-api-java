package com.orgsync.api.model.courses;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 * A model for the courses.
 * 
 * @author steffyj
 * 
 */
public class Course {

    public static final Type TYPE = new TypeToken<Course>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<Course>>() {
    }.getType();

    private int id;
    private String name;
    private String date;
    private String link;
    private int accountId;
    private String section;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final String getDate() {
        return date;
    }

    public final String getLink() {
        return link;
    }

    public final int getAccountId() {
        return accountId;
    }

    public final String getSection() {
        return section;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountId;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + id;
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
        Course other = (Course) obj;
        if (accountId != other.accountId)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
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
        return "Course [id=" + id + ", name=" + name + ", date=" + date + ", link=" + link + ", accountId=" + accountId
                + ", section=" + section + "]";
    }

}
