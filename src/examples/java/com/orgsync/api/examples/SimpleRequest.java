package com.orgsync.api.examples;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.Modules;
import com.orgsync.api.OrgSync;
import com.orgsync.api.OrgsModule;
import com.orgsync.api.Util;
import com.orgsync.api.Version;
import com.orgsync.api.messages.orgs.Org;

public class SimpleRequest {

	public static void main(final String[] args) throws InterruptedException,
			ExecutionException {
		String apiKey = "dd6b9d2beb614611c5eb9f56c34b743d1d86f385";
		String host = "http://localhost:8080/api/";
		ApiClient client = OrgSync.newApiClient(apiKey, Version.V2, host);

		try {
			System.out.println("Requesting orgs");
			OrgsModule module = client.getModule(Modules.ORGS);
			ApiResponse<List<Org>> result = module.getOrgs().get();
			if (result.isSuccess()) {
				System.out.println("Recieved following orgs:");
				System.out.println(Util.joinList(result.getResult(), "\n"));
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
