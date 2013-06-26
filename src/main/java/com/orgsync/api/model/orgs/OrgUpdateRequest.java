package com.orgsync.api.model.orgs;

/**
 * A request to update an org.
 * 
 * @author steffyj
 * 
 */
public class OrgUpdateRequest {

    private Boolean isDisabled;
    private String alternateId;
    private String shortName;

    public final Boolean getIsDisabled() {
        return isDisabled;
    }

    public final OrgUpdateRequest setIsDisabled(final Boolean isDisabled) {
        this.isDisabled = isDisabled;
        return this;
    }

    public final String getAlternateId() {
        return alternateId;
    }

    public final OrgUpdateRequest setAlternateId(final String alternateId) {
        this.alternateId = alternateId;
        return this;
    }

    public final String getShortName() {
        return shortName;
    }

    public final OrgUpdateRequest setShortName(final String shortName) {
        this.shortName = shortName;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alternateId == null) ? 0 : alternateId.hashCode());
        result = prime * result + ((isDisabled == null) ? 0 : isDisabled.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
        OrgUpdateRequest other = (OrgUpdateRequest) obj;
        if (alternateId == null) {
            if (other.alternateId != null)
                return false;
        } else if (!alternateId.equals(other.alternateId))
            return false;
        if (isDisabled == null) {
            if (other.isDisabled != null)
                return false;
        } else if (!isDisabled.equals(other.isDisabled))
            return false;
        if (shortName == null) {
            if (other.shortName != null)
                return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrgUpdateRequest [isDisabled=" + isDisabled + ", alternateId=" + alternateId + ", shortName="
                + shortName + "]";
    }

}
