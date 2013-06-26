package com.orgsync.api.model.checkbooks;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.IdAndName;

/**
 * A single entry in a checkbook.
 * 
 * @author steffyj
 * 
 */
public class CheckbookEntry {

    public static final Type TYPE = new TypeToken<CheckbookEntry>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<CheckbookEntry>>() {
    }.getType();

    private int id;
    private String description;
    private boolean isReadOnly;
    private String amount;
    private Date occurredAt;
    private IdAndName checkbook;

    public final int getId() {
        return id;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean isReadOnly() {
        return isReadOnly;
    }

    public final String getAmount() {
        return amount;
    }

    public final Date getOccurredAt() {
        return occurredAt;
    }

    public final IdAndName getCheckbook() {
        return checkbook;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((checkbook == null) ? 0 : checkbook.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + (isReadOnly ? 1231 : 1237);
        result = prime * result + ((occurredAt == null) ? 0 : occurredAt.hashCode());
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
        CheckbookEntry other = (CheckbookEntry) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (checkbook == null) {
            if (other.checkbook != null)
                return false;
        } else if (!checkbook.equals(other.checkbook))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id != other.id)
            return false;
        if (isReadOnly != other.isReadOnly)
            return false;
        if (occurredAt == null) {
            if (other.occurredAt != null)
                return false;
        } else if (!occurredAt.equals(other.occurredAt))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CheckbookEntry [id=" + id + ", description=" + description + ", isReadOnly=" + isReadOnly + ", amount="
                + amount + ", occurredAt=" + occurredAt + ", checkbook=" + checkbook + "]";
    }

}
