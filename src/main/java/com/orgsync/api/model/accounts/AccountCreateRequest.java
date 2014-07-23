/*
 * Copyright 2014 OrgSync
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

import java.util.LinkedList;
import java.util.List;

/**
 * A request to create an account via the API.
 *
 * @author steffyj
 */
public class AccountCreateRequest {
    private String username;
    private boolean sendWelcome;
    private String welcomeMessage;
    private AccountAttributes accountAttributes;
    private List<Integer> portals = new LinkedList<Integer>();
    private List<Integer> groups = new LinkedList<Integer>();
    private List<String> identificationCardNumbers = new LinkedList<String>();

    public String getUsername() {
        return username;
    }

    public AccountCreateRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isSendWelcome() {
        return sendWelcome;
    }

    public AccountCreateRequest setSendWelcome(boolean sendWelcome) {
        this.sendWelcome = sendWelcome;
        return this;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public AccountCreateRequest setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
        return this;
    }

    public AccountAttributes getAccountAttributes() {
        return accountAttributes;
    }

    public AccountCreateRequest setAccountAttributes(AccountAttributes accountAttributes) {
        this.accountAttributes = accountAttributes;
        return this;
    }

    public List<Integer> getPortals() {
        return portals;
    }

    public AccountCreateRequest setPortals(List<Integer> portals) {
        this.portals = portals;
        return this;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public AccountCreateRequest setGroups(List<Integer> groups) {
        this.groups = groups;
        return this;
    }

    public List<String> getIdentificationCardNumbers() {
        return identificationCardNumbers;
    }

    public AccountCreateRequest setIdentificationCardNumbers(List<String> identificationCardNumbers) {
        this.identificationCardNumbers = identificationCardNumbers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountCreateRequest)) return false;

        AccountCreateRequest that = (AccountCreateRequest) o;

        if (sendWelcome != that.sendWelcome) return false;
        if (accountAttributes != null ? !accountAttributes.equals(that.accountAttributes) : that.accountAttributes != null)
            return false;
        if (groups != null ? !groups.equals(that.groups) : that.groups != null) return false;
        if (identificationCardNumbers != null ? !identificationCardNumbers.equals(that.identificationCardNumbers) : that.identificationCardNumbers != null)
            return false;
        if (portals != null ? !portals.equals(that.portals) : that.portals != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (welcomeMessage != null ? !welcomeMessage.equals(that.welcomeMessage) : that.welcomeMessage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (sendWelcome ? 1 : 0);
        result = 31 * result + (welcomeMessage != null ? welcomeMessage.hashCode() : 0);
        result = 31 * result + (accountAttributes != null ? accountAttributes.hashCode() : 0);
        result = 31 * result + (portals != null ? portals.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        result = 31 * result + (identificationCardNumbers != null ? identificationCardNumbers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountCreateRequest{");
        sb.append("username='").append(username).append('\'');
        sb.append(", sendWelcome=").append(sendWelcome);
        sb.append(", welcomeMessage='").append(welcomeMessage).append('\'');
        sb.append(", accountAttributes=").append(accountAttributes);
        sb.append(", portals=").append(portals);
        sb.append(", groups=").append(groups);
        sb.append(", identificationCardNumbers=").append(identificationCardNumbers);
        sb.append('}');
        return sb.toString();
    }
}
