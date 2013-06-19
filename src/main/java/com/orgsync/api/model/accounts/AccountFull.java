package com.orgsync.api.model.accounts;

import java.util.List;

import com.orgsync.api.model.forms.FormResponse;

public class AccountFull extends AccountDetail {

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
