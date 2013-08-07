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
package com.orgsync.api.model.accounts;

/**
 * A request to update an account.
 * 
 * @author steffyj
 * 
 */
public class AccountUpdateRequest {

    private String username;
    private ElementPair element;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zip;

    public final String getUsername() {
        return username;
    }

    public final AccountUpdateRequest setUsername(final String username) {
        this.username = username;
        return this;
    }

    public final ElementPair getElement() {
        return element;
    }

    public final AccountUpdateRequest setElement(final ElementPair element) {
        this.element = element;
        return this;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final AccountUpdateRequest setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public final String getLastName() {
        return lastName;
    }

    public final AccountUpdateRequest setLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public final String getMiddleInitial() {
        return middleInitial;
    }

    public final AccountUpdateRequest setMiddleInitial(final String middleInitial) {
        this.middleInitial = middleInitial;
        return this;
    }

    public final String getEmail() {
        return email;
    }

    public final AccountUpdateRequest setEmail(final String email) {
        this.email = email;
        return this;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final AccountUpdateRequest setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public final String getAddress() {
        return address;
    }

    public final AccountUpdateRequest setAddress(final String address) {
        this.address = address;
        return this;
    }

    public final String getCity() {
        return city;
    }

    public final AccountUpdateRequest setCity(final String city) {
        this.city = city;
        return this;
    }

    public final String getState() {
        return state;
    }

    public final AccountUpdateRequest setState(final String state) {
        this.state = state;
        return this;
    }

    public final String getZip() {
        return zip;
    }

    public final AccountUpdateRequest setZip(final String zip) {
        this.zip = zip;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((element == null) ? 0 : element.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
        AccountUpdateRequest other = (AccountUpdateRequest) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (element == null) {
            if (other.element != null)
                return false;
        } else if (!element.equals(other.element))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (middleInitial == null) {
            if (other.middleInitial != null)
                return false;
        } else if (!middleInitial.equals(other.middleInitial))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (zip == null) {
            if (other.zip != null)
                return false;
        } else if (!zip.equals(other.zip))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AccountUpdateRequest [username=" + username + ", elementId=" + element + ", firstName=" + firstName
                + ", lastName=" + lastName + ", middleInitial=" + middleInitial + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
    }

    public static final class ElementPair {
        private final int elementId;
        private final String elementValue;

        public ElementPair(final int elementId, final String elementValue) {
            super();
            this.elementId = elementId;
            this.elementValue = elementValue;
        }

        public final int getElementId() {
            return elementId;
        }

        public final String getElementValue() {
            return elementValue;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + elementId;
            result = prime * result + ((elementValue == null) ? 0 : elementValue.hashCode());
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
            ElementPair other = (ElementPair) obj;
            if (elementId != other.elementId)
                return false;
            if (elementValue == null) {
                if (other.elementValue != null)
                    return false;
            } else if (!elementValue.equals(other.elementValue))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "ElementPair [elementId=" + elementId + ", elementValue=" + elementValue + "]";
        }

    }

}
