package com.orgsync.api;

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
