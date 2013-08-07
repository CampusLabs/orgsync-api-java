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
package com.orgsync.api.integration;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.hamcrest.core.IsCollectionContaining;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.ApiClient;
import com.orgsync.api.ApiResponse;
import com.orgsync.api.OrgSync;
import com.orgsync.api.Resource;
import com.typesafe.config.Config;

public class BaseIntegrationTest<T> {

    public static final String API_KEY = DbTemplate.getString("api_key");
    public static final String HOST = "http://localhost:8080/api/v2";

    private static final Map<Resource<?>, ApiClient> clients = new HashMap<Resource<?>, ApiClient>();

    final ApiClient client = OrgSync.newApiClient(API_KEY, HOST);
    private final T resource;

    public BaseIntegrationTest(final Resource<T> resourceKey) {
        resource = client.getResource(resourceKey);
        clients.put(resourceKey, client);
    }

    public T getResource() {
        return resource;
    }

    public <R> R getResult(final ListenableFuture<ApiResponse<R>> future) throws InterruptedException,
            ExecutionException {
        return future.get().getResult();
    }

    public void testContainsIds(final List<? extends Object> results, final List<? extends Config> configs)
            throws Exception {
        List<Integer> expectedIds = getIdsForConfigs(configs);
        List<Integer> returnedIds = getIdsForObjects(results);

        assertThat(returnedIds, IsCollectionContaining.hasItems(expectedIds.toArray(new Integer[expectedIds.size()])));
    }

    public List<Integer> getIdsForConfigs(final List<? extends Config> configs) {
        List<Integer> ids = new ArrayList<Integer>();
        for (Config config : configs) {
            ids.add(config.getInt("id"));
        }

        return ids;
    }

    public List<Integer> getIdsForObjects(final List<? extends Object> results) throws Exception {
        List<Integer> ids = new ArrayList<Integer>();
        for (Object obj : results) {
            Integer id = (Integer) obj.getClass().getMethod("getId").invoke(obj);
            ids.add(id);
        }

        return ids;
    }

    public static void cleanup(final Resource<?> resourceKey) {
        clients.get(resourceKey).destroy();
    }

}
