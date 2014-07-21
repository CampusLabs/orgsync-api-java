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

import com.orgsync.api.model.forms.FormUpdate;

import java.util.LinkedList;
import java.util.List;

/**
 * A request to update an account.
 * 
 * @author steffyj
 * 
 */
public class AccountUpdateRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zip;
    private List<FormUpdate> profileResponses = new LinkedList<FormUpdate>();

    public final String getUsername() {
        return username;
    }

    public final AccountUpdateRequest setUsername(final String username) {
        this.username = username;
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

    public List<FormUpdate> getProfileResponses() {
        return profileResponses;
    }

    public AccountUpdateRequest addProfileUpdate(FormUpdate update) {
        profileResponses.add(update);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountUpdateRequest)) return false;

        AccountUpdateRequest that = (AccountUpdateRequest) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (middleInitial != null ? !middleInitial.equals(that.middleInitial) : that.middleInitial != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (profileResponses != null ? !profileResponses.equals(that.profileResponses) : that.profileResponses != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleInitial != null ? middleInitial.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (profileResponses != null ? profileResponses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountUpdateRequest{");
        sb.append("username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", middleInitial='").append(middleInitial).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zip='").append(zip).append('\'');
        sb.append(", profileResponses=").append(profileResponses);
        sb.append('}');
        return sb.toString();
    }
}
