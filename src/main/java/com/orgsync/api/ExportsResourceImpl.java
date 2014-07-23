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
 * The implementation of the exports resource.
 *
 * @author steffyj
 */
/* package */class ExportsResourceImpl extends BaseResource implements ExportsResource {

    /* package */ExportsResourceImpl(final ApiClientImpl client) {
        super(client, "/exports");
    }

    @Override
    public Future<ApiResponse<List<AccountFull>>> getAccounts() {
        return null;
    }

}
