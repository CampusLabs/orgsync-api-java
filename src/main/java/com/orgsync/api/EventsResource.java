package com.orgsync.api;

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.events.Event;
import com.orgsync.api.model.events.EventQueryParams;

public interface EventsResource {

    public ListenableFuture<ApiResponse<List<Event>>> getEvents(EventQueryParams params);

    public ListenableFuture<ApiResponse<List<Event>>> getOrgEvents(int orgId, EventQueryParams params);

}
