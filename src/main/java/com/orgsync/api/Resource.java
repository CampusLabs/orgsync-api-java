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
