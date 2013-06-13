package com.orgsync.api;

import com.orgsync.api.impl.ApiClientImpl;
import com.orgsync.api.impl.OrgsModuleImpl;

public final class Modules {

	private Modules() {
	}

	public static final Module<OrgsModule> ORGS = new Module<OrgsModule>() {
		@Override
		public OrgsModule get(final ApiClientImpl client) {
			return new OrgsModuleImpl(client);
		}
	};

}
