package com.orgsync.api.model.groups;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 * A group within an org.
 * 
 * @author steffyj
 * 
 */
public class Group {

    public static final Type TYPE = new TypeToken<Group>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<Group>>() {
    }.getType();

    private int id;
    private String name;
    private List<Integer> accountIds;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final List<Integer> getAccountIds() {
        return accountIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountIds == null) ? 0 : accountIds.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Group other = (Group) obj;
        if (accountIds == null) {
            if (other.accountIds != null)
                return false;
        } else if (!accountIds.equals(other.accountIds))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + ", accountIds=" + accountIds + "]";
    }

}
