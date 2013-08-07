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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.OrgsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.Success;

public class AddAccountsRequest {

    public static void main(final String[] args) throws InterruptedException,
            ExecutionException {
        String apiKey = "dd6b9d2beb614611c5eb9f56c34b743d1d86f385";
        String host = "https://api.orgsync.com/api/v2";
        ApiClient client = OrgSync.newApiClient(apiKey, host);

        try {
            System.out.println("Requesting orgs");
            OrgsResource resource = client.getResource(Resources.ORGS);

            int orgId = 46052;
            List<Integer> ids = Arrays.asList(5666, 1575);

            ApiResponse<Success> result = resource.addAccounts(orgId, ids)
                    .get();
            if (result.isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.err.println("Error attempting to retrieve orgs!");
                System.err.println(result.getError());
            }
            System.out.println("Cleanup client");
        } finally {
            client.destroy();
        }

        System.out.println("Exiting...");
    }

}
