/*
 * Copyright 2013 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orgsync.api;

/**
 * <p>
 * The entry point for creating clients for the OrgSync API V2. Static factory method are available to retrieve
 * instances of the API client.
 * <p>
 * Use the {@link #newApiClient(String)} method to create an {@link ApiClient} with your OrgSync API key. This client
 * provides access to the various resources exposed by V2 of the OrgSync API.
 * 
 * <p>
 * See: <a href="">https://api.orgsync.com/api/docs/v2</a>
 * 
 * @author steffyj
 * 
 */
public final class OrgSync {

    private OrgSync() {
    }

    /**
     * Create a new V2 API client with the given OrgSync API key.
     * 
     * @param apiKey
     *            the api key for your community
     * @return the api client
     */
    public static final ApiClient newApiClient(final String apiKey) {
        return newApiClient(apiKey, ApiClientImpl.DEFAULT_HOST);
    }

    /**
     * Create a new V2 API client with the given OrgSync API key and host. Most clients will not need to update the host
     * used.
     * 
     * @param apiKey
     *            the api key for your community
     * @param host
     *            the host to connect to
     * @return the api client
     */
    public static ApiClient newApiClient(final String apiKey, final String host) {
        return new ApiClientImpl(apiKey, host);
    }

}
