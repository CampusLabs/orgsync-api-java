package com.orgsync.api;

/**
 * Constants to pass to {@link ApiClient#getResource(Resource)} to retrieve access to a given resource.
 * 
 * @author steffyj
 * 
 */
public final class Resources {

    private Resources() {
        // no create...
    }

    /**
     * The accounts resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/accounts">https://api.orgsync.com/api/docs/v2/accounts</a>
     */
    public static final Resource<AccountsResource> ACCOUNTS = new Resource<AccountsResource>() {
        @Override
        AccountsResource get(final ApiClientImpl client) {
            return new AccountsResourceImpl(client);
        }
    };

    /**
     * The checkbooks resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/checkbooks">https://api.orgsync.com/api/docs/v2/checkbooks</a>
     */
    public static final Resource<CheckbooksResource> CHECKBOOKS = new Resource<CheckbooksResource>() {
        @Override
        CheckbooksResource get(final ApiClientImpl client) {
            return new CheckbooksResourceImpl(client);
        }
    };

    /**
     * The events resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/events">https://api.orgsync.com/api/docs/v2/events</a>
     */
    public static final Resource<EventsResource> EVENTS = new Resource<EventsResource>() {
        @Override
        EventsResource get(final ApiClientImpl client) {
            return new EventsResourceImpl(client);
        }
    };

    /**
     * The forms resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/forms">https://api.orgsync.com/api/docs/v2/forms</a>
     */
    public static final Resource<FormsResource> FORMS = new Resource<FormsResource>() {
        @Override
        FormsResource get(final ApiClientImpl client) {
            return new FormsResourceImpl(client);
        }
    };

    /**
     * The groups resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/groups">https://api.orgsync.com/api/docs/v2/groups</a>
     */
    public static final Resource<GroupsResource> GROUPS = new Resource<GroupsResource>() {
        @Override
        GroupsResource get(final ApiClientImpl client) {
            return new GroupsResourceImpl(client);
        }
    };

    /**
     * The identification cards resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/identification_cards">https://api.orgsync.com/api/docs/v2/
     * identification_cards</a>
     */
    public static final Resource<IdentificationCardsResource> IDENTIFICATIONS_CARDS = new Resource<IdentificationCardsResource>() {
        @Override
        IdentificationCardsResource get(final ApiClientImpl client) {
            return new IdentificationCardsResourceImpl(client);
        }
    };

    /**
     * The membership logs resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/org_membership_log_entries">https://api.orgsync.com/api/docs/v2/
     * org_membership_log_entries</a>
     */
    public static final Resource<MembershipLogsResource> MEMBERSHIP_LOGS = new Resource<MembershipLogsResource>() {
        @Override
        MembershipLogsResource get(final ApiClientImpl client) {
            return new MembershipLogsResourceImpl(client);
        }
    };

    /**
     * The orgs resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/orgs">https://api.orgsync.com/api/docs/v2/orgs</a>
     */
    public static final Resource<OrgsResource> ORGS = new Resource<OrgsResource>() {
        @Override
        public OrgsResource get(final ApiClientImpl client) {
            return new OrgsResourceImpl(client);
        }
    };

    /**
     * The timesheets resource.
     * <p>
     * See <a href="https://api.orgsync.com/api/docs/v2/timesheets">https://api.orgsync.com/api/docs/v2/timesheets</a>
     */
    public static final Resource<TimesheetsResource> TIMESHEETS = new Resource<TimesheetsResource>() {
        @Override
        TimesheetsResource get(final ApiClientImpl client) {
            return new TimesheetsResourceImpl(client);
        }
    };
}
