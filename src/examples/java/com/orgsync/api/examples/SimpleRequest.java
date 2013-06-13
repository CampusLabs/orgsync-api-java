package com.orgsync.api.examples;

import java.util.List;

import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSyncRestClient;
import com.orgsync.api.messages.Org;

public class SimpleRequest {

	public static void main(final String[] args) {
		OrgSyncRestClient client = new OrgSyncRestClient(
				"dd6b9d2beb614611c5eb9f56c34b743d1d86f385");

		System.out.println("Requesting orgs");
		ApiResponse<List<Org>> result = client.getOrganizations();

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
