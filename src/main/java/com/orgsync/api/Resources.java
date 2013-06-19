package com.orgsync.api;

public final class Resources {

    private Resources() {
        // no create...
    }

    public static final Resource<OrgsResource> ORGS = new Resource<OrgsResource>() {
        @Override
        public OrgsResource get(final ApiClientImpl client) {
            return new OrgsResourceImpl(client);
        }
    };

    public static final Resource<AccountsResource> ACCOUNTS = new Resource<AccountsResource>() {
        @Override
        AccountsResource get(final ApiClientImpl client) {
            return new AccountsResourceImpl(client);
        }
    };

}
