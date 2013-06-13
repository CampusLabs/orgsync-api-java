package com.orgsync.api.examples;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.Modules;
import com.orgsync.api.OrgSync;
import com.orgsync.api.OrgsModule;
import com.orgsync.api.messages.Org;

public class SimpleRequest {

	public static void main(final String[] args) throws InterruptedException,
			ExecutionException {
		ApiClient client = OrgSync
				.newApiClient("dd6b9d2beb614611c5eb9f56c34b743d1d86f385");

		System.out.println("Requesting orgs");
		OrgsModule module = client.getModule(Modules.ORGS);
		ApiResponse<List<Org>> result = module.getOrgs().get();

		if (result.isSuccess()) {
			System.out.println("Recieved following orgs:");
			System.out.println(joinList(result.getResult(), "\n"));
		} else {
			System.err.println("Error attempting to retrieve orgs!");
			System.err.println(result.getError());
		}

		System.out.println("Cleanup client");
		client.destroy();

		System.out.println("Exiting...");
	}

	private static <T> String joinList(final List<T> list, final String sep) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			str.append(list.get(i).toString());
			if (i < list.size() - 1) {
				str.append(sep);
			}
		}

		return str.toString();
	}

}
