package com.orgsync.api;

/**
 * A Resource factory really. This class can create a resource given an {@link ApiClientImpl}. Actual instances of these
 * are made available in the {@link Resources} class as constants that can be passed to
 * {@link ApiClient#getResource(Resource)}.
 * 
 * @author steffyj
 * 
 * @param <T>
 *            the type of resource to return
 */
public abstract class Resource<T> {

    /**
     * Get the resource of this type.
     * 
     * @param client
     *            the api client to use
     * @return the resource
     */
    /* package */abstract T get(ApiClientImpl client);

}
