package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.events.Event;
import com.orgsync.api.model.events.EventQueryParams;

/**
 * <p>
 * Access to the events resource from the OrgSync API. This allows a client to query the events and get a list of events
 * for a given org.
 * 
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/events">https://api.orgsync.com/api/docs/v2/events</a>
 * 
 * @author steffyj
 * 
 */
public interface EventsResource {

    /**
     * Get A list of events based on the given {@link EventQueryParams}. To get all events, pass a new
     * {@link EventQueryParams} with no fields set.
     * 
     * @param params
     *            the params to search by
     * @return a future to the response of a list of events
     */
    public ListenableFuture<ApiResponse<List<Event>>> getEvents(EventQueryParams params);

    /**
     * Get the events for a given org and query params. TO get all events, use a new {@link EventQueryParams} with no
     * fields set.
     * 
     * @param orgId
     *            the org to get events for
     * @param params
     *            the params to search by
     * @return a future to the response of a list of events
     */
    public ListenableFuture<ApiResponse<List<Event>>> getOrgEvents(int orgId, EventQueryParams params);

}
