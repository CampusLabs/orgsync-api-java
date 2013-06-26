package com.orgsync.api.model.checkbooks;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.IdAndName;

/**
 * A model for a checkbook.
 * 
 * @author steffyj
 * 
 */
public class Checkbook {

    public static final Type TYPE = new TypeToken<Checkbook>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<Checkbook>>() {
    }.getType();

    private int id;
    private String name;
    private boolean isReadOnly;
    private String balance;
    private IdAndName org;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final boolean isReadOnly() {
        return isReadOnly;
    }

    public final String getBalance() {
        return balance;
    }

    public final IdAndName getOrg() {
        return org;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + id;
        result = prime * result + (isReadOnly ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((org == null) ? 0 : org.hashCode());
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
        Checkbook other = (Checkbook) obj;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        if (id != other.id)
            return false;
        if (isReadOnly != other.isReadOnly)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (org == null) {
            if (other.org != null)
                return false;
        } else if (!org.equals(other.org))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Checkbook [id=" + id + ", name=" + name + ", isReadOnly=" + isReadOnly + ", balance=" + balance
                + ", org=" + org + "]";
    }

}
