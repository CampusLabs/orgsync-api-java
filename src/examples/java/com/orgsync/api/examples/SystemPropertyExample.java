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
package com.orgsync.api.examples;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.orgsync.api.AccountsResource;
import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;

/**
 * This example grabs the API key from a Java system property and make a simple simple request. To run this class, make
 * sure to set the api key on the command line with '-D', loading a properties file, or setting them some other way.
 * 
 * @author steffyj
 * 
 */
public class SystemPropertyExample {

    private static final String API_KEY_PROPERTY = "orgsync.api.key";

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        String apiKey = System.getProperty(API_KEY_PROPERTY);

        if (apiKey == null) {
            throw new IllegalStateException("The system property " + API_KEY_PROPERTY + " must be set!  " +
                    "It can be set on the command line with the '-D' flag to the JVM.");
        }

        ApiClient apiClient = OrgSync.newApiClient(apiKey);

        try {
            AccountsResource accountsResource = apiClient.getResource(Resources.ACCOUNTS);
            ApiResponse<List<Account>> apiResponse = accountsResource.getAccounts().get();

            System.out.println("Call successful? " + apiResponse.isSuccess());
        } finally {
            apiClient.destroy();
        }
    }

}
