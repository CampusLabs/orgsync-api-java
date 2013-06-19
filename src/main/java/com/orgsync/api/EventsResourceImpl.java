package com.orgsync.api;

import static com.orgsync.api.Util.checkNotNull;

import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.events.Event;
import com.orgsync.api.model.events.EventQueryParams;

/*package*/class EventsResourceImpl extends BaseResource implements EventsResource {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /* package */EventsResourceImpl(final ApiClientImpl client) {
        super(client);
    }

    @Override
    public ListenableFuture<ApiResponse<List<Event>>> getEvents(final EventQueryParams params) {
        checkNotNull(params);

        return getResponse(RequestParams.get("/events", toParamsMap(params)), new TypeToken<List<Event>>() {
        }.getType());
    }

    @Override
    public ListenableFuture<ApiResponse<List<Event>>> getOrgEvents(final int orgId, final EventQueryParams params) {
        checkNotNull(params);

        String endpoint = String.format("/orgs/%d/events", orgId);
        return getResponse(RequestParams.get(endpoint, toParamsMap(params)), new TypeToken<List<Event>>() {
        }.getType());
    }

    private FluentStringsMap toParamsMap(final EventQueryParams params) {
        FluentStringsMap map = new FluentStringsMap();

        if (params.getKeyword() != null) {
            map.add("keyword", params.getKeyword());
        }

        if (params.getStartDate() != null) {
            map.add("start_date", dateFormat.format(params.getStartDate()));
        }

        if (params.getEndDate() != null) {
            map.add("end_date", dateFormat.format(params.getEndDate()));
        }

        return map;
    }
}
