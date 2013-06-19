package com.orgsync.api.examples;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.OrgsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.Util;
import com.orgsync.api.Version;
import com.orgsync.api.model.orgs.Org;
import com.orgsync.api.model.orgs.OrgAccount;

public class SimpleRequest {

    public static void main(final String[] args) throws InterruptedException,
            ExecutionException {
        String apiKey = "dd6b9d2beb614611c5eb9f56c34b743d1d86f385";
        String host = "https://api.orgsync.com/api/";
        ApiClient client = OrgSync.newApiClient(apiKey, Version.V2, host);

        try {
            System.out.println("Requesting orgs");
            OrgsResource resource = client.getResource(Resources.ORGS);
            ApiResponse<List<Org>> orgsResponse = resource.getOrgs().get();

            if (isSuccess(orgsResponse)) {
                System.out.println("Recieved following orgs:");
                System.out.println(Util.joinList(orgsResponse.getResult(), "\n"));
            }

            ApiResponse<List<OrgAccount>> accountsResponse = resource
                    .listAccounts(225).get();
            if (isSuccess(accountsResponse)) {
                System.out.println("Received list of accounts: \n"
                        + Util.joinList(accountsResponse.getResult(), "\n"));
            }

            System.out.println("Cleanup client");
        } finally {
            client.destroy();
        }

        System.out.println("Exiting...");
    }

    private static boolean isSuccess(final ApiResponse<?> response) {
        if (response.isSuccess())
            return true;

        System.out.println("Error making request: " + response.getError());
        return false;
    }
}
