package com.orgsync.api;

import com.ning.http.client.AsyncHttpClient;

/**
 * <p>
 * A client to the OrgSync API. A new instance can be created from the {@link OrgSync} class using one of the factory
 * methods (e.g. {@link OrgSync#newApiClient(String)}). The client makes the various API resources avialable by
 * providing access to the resource using {@link #getResource(Resource)} with a value from the {@link Resources} class.
 * <p>
 * The {@link ApiClient} provides access to the underlying {@link AsyncHttpClient} and also allows a user to
 * {@link #setHttpClient(AsyncHttpClient)} for any special needs. Most users will not need to access these methods.
 * <p>
 * Finally, a client can be {@link #destroy()}ed to free the underlying resources of the http client. This must be
 * called for the JVM to exit cleanly.
 * 
 * @author steffyj
 * 
 */
public interface ApiClient {

    /**
     * Clean up any resources held by the underlying http client. This method must be called for the application to exit
     * cleanly.
     */
    public void destroy();

    /**
     * Get a specific resource from the API. The valid resources are listed in the {@link Resources} class.
     * 
     * @param resource
     *            the resource instances from {@link Resources}
     * @return the resource instance
     */
    public <T> T getResource(Resource<T> resource);

    /**
     * Get the API key that is being used by this client.
     * 
     * @return the api key
     */
    public String getApiKey();

    /**
     * Get the underlying http client for this api client.
     * 
     * @return the http client
     */
    public AsyncHttpClient getHttpClient();

    /**
     * Set the client to use a given http client. This allows clients to customize the values used in the http client.
     * 
     * @param client
     *            the http client to use
     * @return the api client
     */
    public ApiClient setHttpClient(AsyncHttpClient client);

}
