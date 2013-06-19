package com.orgsync.api.model.forms;

import java.util.Date;
import java.util.List;

import com.orgsync.api.model.IdAndName;

public class Form {

    private int id;
    private String name;
    private String shortName;
    private String description;
    private Date beginsAt;
    private Date endsAt;
    private boolean isPublished;
    private IdAndName category;
    private int formPagesCount;
    private int submissionLimit;
    private int submissionCount;
    private List<Integer> submissionIds;

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final String getShortName() {
        return shortName;
    }

    public final String getDescription() {
        return description;
    }

    public final Date getBeginsAt() {
        return beginsAt;
    }

    public final Date getEndsAt() {
        return endsAt;
    }

    public final boolean isPublished() {
        return isPublished;
    }

    public final IdAndName getCategory() {
        return category;
    }

    public final int getFormPagesCount() {
        return formPagesCount;
    }

    public final int getSubmissionLimit() {
        return submissionLimit;
    }

    public final int getSubmissionCount() {
        return submissionCount;
    }

    public final List<Integer> getSubmissionIds() {
        return submissionIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beginsAt == null) ? 0 : beginsAt.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((endsAt == null) ? 0 : endsAt.hashCode());
        result = prime * result + formPagesCount;
        result = prime * result + id;
        result = prime * result + (isPublished ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
        result = prime * result + submissionCount;
        result = prime * result + ((submissionIds == null) ? 0 : submissionIds.hashCode());
        result = prime * result + submissionLimit;
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
        Form other = (Form) obj;
        if (beginsAt == null) {
            if (other.beginsAt != null)
                return false;
        } else if (!beginsAt.equals(other.beginsAt))
            return false;
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
        if (endsAt == null) {
            if (other.endsAt != null)
                return false;
        } else if (!endsAt.equals(other.endsAt))
            return false;
        if (formPagesCount != other.formPagesCount)
            return false;
        if (id != other.id)
            return false;
        if (isPublished != other.isPublished)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (shortName == null) {
            if (other.shortName != null)
                return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        if (submissionCount != other.submissionCount)
            return false;
        if (submissionIds == null) {
            if (other.submissionIds != null)
                return false;
        } else if (!submissionIds.equals(other.submissionIds))
            return false;
        if (submissionLimit != other.submissionLimit)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Form [id=" + id + ", name=" + name + ", shortName=" + shortName + ", description=" + description
                + ", beginsAt=" + beginsAt + ", endsAt=" + endsAt + ", isPublished=" + isPublished + ", category="
                + category + ", formPagesCount=" + formPagesCount + ", submissionLimit=" + submissionLimit
                + ", submissionCount=" + submissionCount + ", submissionIds=" + submissionIds + "]";
    }

}
