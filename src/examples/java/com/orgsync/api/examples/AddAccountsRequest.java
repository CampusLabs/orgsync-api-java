package com.orgsync.api.examples;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.OrgsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.Version;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.orgs.AddAccounts;

public class AddAccountsRequest {

    public static void main(final String[] args) throws InterruptedException,
            ExecutionException {
        String apiKey = "dd6b9d2beb614611c5eb9f56c34b743d1d86f385";
        String host = "http://localhost:8080/api/";
        ApiClient client = OrgSync.newApiClient(apiKey, Version.V2, host);

        try {
            System.out.println("Requesting orgs");
            OrgsResource resource = client.getResource(Resources.ORGS);

            int orgId = 46052;
            List<Integer> ids = Arrays.asList(5666, 1575);

            AddAccounts accounts = new AddAccounts(orgId, ids);

            ApiResponse<Success> result = resource.addAccounts(accounts).get();
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
