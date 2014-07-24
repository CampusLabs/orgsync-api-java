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

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.accounts.AccountFull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The implementation of the exports resource.
 *
 * @author steffyj
 */
/* package */class ExportsResourceImpl extends BaseResource implements ExportsResource {

    private static final ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("api-exports"));

    /* package */ExportsResourceImpl(final ApiClientImpl client) {
        super(client, "/exports");
    }

    @Override
    public Future<ApiResponse<List<AccountFull>>> getAccounts() {
        return executor.submit(new DataExportTask<List<AccountFull>>(this, "accounts"));
    }

    /* package */Future<ApiResponse<ExportRequest>> requestToken(String exportType) {
        String url = String.format("%s/%s", getEndpoint(), exportType);
        return getResponse(RequestParams.get(url), ExportRequest.TYPE);
    }

    /* package */static class ExportRequest {

        public static final Type TYPE = new TypeToken<ExportRequest>() {
        }.getType();

        private String exportToken;

        public String getExportToken() {
            return exportToken;
        }
    }
}
