package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;

import java.util.List;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.events.Event;
import com.orgsync.api.model.events.EventQueryParams;

/**
 * Implementation of events resource.
 * 
 * @author steffyj
 * 
 */
/* package */class EventsResourceImpl extends BaseResource implements EventsResource {

    /* package */EventsResourceImpl(final ApiClientImpl client) {
        super(client, "/events");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Event>>> getEvents(final EventQueryParams params) {
        checkNotNull(params);

        return getResponse(RequestParams.get(getEndpoint(), toParamsMap(params)), Event.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Event>>> getOrgEvents(final int orgId, final EventQueryParams params) {
        checkNotNull(params);

        String endpoint = listFor("/orgs/%d", orgId);
        return getResponse(RequestParams.get(endpoint, toParamsMap(params)), Event.LIST_TYPE);
    }

    /**
     * Create the query params map for the given event query.
     * 
     * @param params
     *            the event query
     * @return the query params map
     */
    private FluentStringsMap toParamsMap(final EventQueryParams params) {
        FluentStringsMap map = new FluentStringsMap();

        if (params.getKeyword() != null) {
            map.add("keyword", params.getKeyword());
        }

        if (params.getStartDate() != null) {
            map.add("start_date", dateToQueryParam(params.getStartDate()));
        }

        if (params.getEndDate() != null) {
            map.add("end_date", dateToQueryParam(params.getEndDate()));
        }

        return map;
    }
}
