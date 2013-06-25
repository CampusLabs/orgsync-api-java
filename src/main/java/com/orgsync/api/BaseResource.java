package com.orgsync.api;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;

/**
 * <p>
 * An Abstract Base Class (ABC) for resources. It provides some commonly needed functions to map common rails idioms.
 * For instance, {@link #list(Type)} will return the standard index for a given endpoint and create the correct type.
 * <p>
 * Some other helpers include {@link #dateToQueryParam(Date)} for converting a date to the correct format. Also the
 * {@link #checkAddField(FluentStringsMap, String, Object)} will add a given field to the query params with a given name
 * if it is not null. This is useful for the various <code>*UpdateRequest</code> methods.
 * 
 * 
 * @author steffyj
 * 
 */
/* package */abstract class BaseResource {

    /**
     * The date format to use (e.g. in query params) /* package
     */
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    static {
        // Don't forget to set the timezone...
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private final ApiClientImpl client;

    private final String endpoint;

    /**
     * Every resource needs an {@link ApiClientImpl} to make requests the and the base endpoint.
     * 
     * @param client
     *            the client to use for making requests
     * @param endpoint
     *            the base endpoint for this resource
     */
    /* package */BaseResource(final ApiClientImpl client, final String endpoint) {
        this.client = client;
        this.endpoint = endpoint;
    }

    /**
     * Get a response for the request params and type... delegate to client.
     * 
     * @param params
     *            the request info
     * @param type
     *            the type that is returned on success
     * @return the future of the response for the request
     */
    /* package */<T> ListenableFuture<ApiResponse<T>> getResponse(final RequestParams params, final Type type) {
        return client.getResponse(params, type);
    }

    /**
     * Add a value to the params with the field name if it is not <code>null</code>.
     * 
     * @param params
     *            the params to add to
     * @param field
     *            the field name in the params
     * @param value
     *            the value to add if not null
     */
    void checkAddField(final FluentStringsMap params, final String field, final Object value) {
        if (value != null) {
            params.add(field, value.toString());
        }
    }

    /**
     * Convert a date to a query param string.
     * 
     * @param date
     *            the date to convert
     * @return the string for the query param
     */
    /* package */String dateToQueryParam(final Date date) {
        return dateFormat.format(date);
    }

    /**
     * Get the endpoint.
     * 
     * @return the endpoint
     */
    String getEndpoint() {
        return endpoint;
    }

    /**
     * Get the show URL for a given ID.
     * 
     * @param id
     *            the id to get the show URL for
     * @return the show URL
     */
    String showFor(final int id) {
        return String.format("%s/%d", endpoint, id);
    }

    /**
     * Get the list URL for a given prefix format and args.
     * 
     * @param prefixFormat
     *            the format string for the prefix
     * @param args
     *            the args to pass to format
     * @return the prefixed list URL
     */
    String listFor(final String prefixFormat, final Object... args) {
        return String.format(prefixFormat, args) + endpoint;
    }

    /**
     * List this resource by calling the endpoint and use the given type.
     * 
     * @param type
     *            the return type of the list
     * @return the future to the response
     */
    <T> ListenableFuture<ApiResponse<T>> list(final Type type) {
        return list("", type);
    }

    /**
     * Call to list with some given prefix. This is a common idiom in the API (e.g. /orgs/123/accounts).
     * 
     * @param prefix
     *            the prefix to use
     * @param type
     *            the type that is returned
     * @return the future to the response
     */
    <T> ListenableFuture<ApiResponse<T>> list(final String prefix, final Type type, final Object... args) {
        return getResponse(RequestParams.get(listFor(prefix, args)), type);
    }

    /**
     * A standard show for a given id.
     * 
     * @param id
     *            the id to show
     * @param type
     *            the type of result
     * @return the future to the response
     */
    <T> ListenableFuture<ApiResponse<T>> show(final int id, final Type type) {
        return getResponse(RequestParams.get(showFor(id)), type);
    }

    /**
     * Update a given id using the given params.
     * 
     * @param id
     *            the id to update
     * @param params
     *            the query params to use in the update
     * @param type
     *            the type that is returned
     * @return the future to the response
     */
    <T> ListenableFuture<ApiResponse<T>> update(final int id, final FluentStringsMap params, final Type type) {
        return getResponse(RequestParams.put(showFor(id), params), type);
    }

    /**
     * Create for the resource using the given params.
     * 
     * @param params
     *            the params to create with
     * @param type
     *            the returned type
     * @return a future to the response of the type
     */
    <T> ListenableFuture<ApiResponse<T>> create(final FluentStringsMap params, final Type type) {
        return getResponse(RequestParams.post(getEndpoint(), params), type);
    }

    /**
     * Delete the given id.
     * 
     * @param id
     *            the id to delete
     * @param type
     *            the response type
     * @return the future to the response
     */
    <T> ListenableFuture<ApiResponse<T>> delete(final int id, final Type type) {
        return getResponse(RequestParams.delete(showFor(id)), type);
    }

}
