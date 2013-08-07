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
package com.orgsync.api.model.identification_cards;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 * An identification card.
 * 
 * @author steffyj
 * 
 */
public class IdentificationCard {

    public static final Type TYPE = new TypeToken<IdentificationCard>() {
    }.getType();

    public static final Type LIST_TYPE = new TypeToken<List<IdentificationCard>>() {
    }.getType();

    private int id;
    private String number;
    private int accountId;
    private String email;

    public final int getId() {
        return id;
    }

    public final String getNumber() {
        return number;
    }

    public final int getAccountId() {
        return accountId;
    }

    public final String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountId;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;
        result = prime * result + ((number == null) ? 0 : number.hashCode());
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
        IdentificationCard other = (IdentificationCard) obj;
        if (accountId != other.accountId)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id != other.id)
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "IdentificationCard [id=" + id + ", number=" + number + ", accountId=" + accountId + ", email=" + email
                + "]";
    }

}
