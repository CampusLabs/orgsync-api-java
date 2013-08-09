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

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ProxyServer;
import com.orgsync.api.AccountsResource;
import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.Resources;
import com.orgsync.api.model.accounts.Account;

/**
 * An example that uses a proxy (localhost:8888) for communicating with the OrgSync API.
 * 
 * @author steffyj
 * 
 */
public class UseProxy {

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        String apiKey = "dd6b9d2beb614611c5eb9f56c34b743d1d86f385";
        ApiClient apiClient = OrgSync.newApiClient(apiKey);

        ProxyServer proxy = new ProxyServer("localhost", 8888);
        AsyncHttpClientConfig config = new AsyncHttpClientConfig.Builder().
                setProxyServer(proxy).
                build();
        AsyncHttpClient httpClient = new AsyncHttpClient(config);

        apiClient.setHttpClient(httpClient);

        try {
            AccountsResource accountsResource = apiClient.getResource(Resources.ACCOUNTS);
            ApiResponse<List<Account>> apiResponse = accountsResource.getAccounts().get();

            System.out.println("Call successful? " + apiResponse.isSuccess());
        } finally {
            apiClient.destroy();
        }
    }

}
