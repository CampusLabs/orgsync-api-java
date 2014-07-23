/*
 * Copyright 2014 OrgSync
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

import com.orgsync.api.model.accounts.AccountFull;

import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 * Access to data exports from the API, which provide a convienient way to request large amounts of data
 * in a single call.  The drawback is that these requests can take a significant amount of time.
 * </p>
 * See: <a href="https://orgsync.com/36548/files/496821/show">https://orgsync.com/36548/files/496821/show</a>
 *
 * @author steffyj
 */
public interface ExportsResource {

    /**
     * Get all of the detailed account information about every account.
     *
     * @return A future to the response with all of the full account information
     */
    public Future<ApiResponse<List<AccountFull>>> getAccounts();

}
