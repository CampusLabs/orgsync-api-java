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

import com.orgsync.api.model.ApiError;
import com.orgsync.api.model.accounts.AccountFull;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * A task that handles the full life-cycle of getting an export.
 *
 * @author steffyj
 */
/* package */class DataExportTask<T> implements Callable<ApiResponse<T>> {

    private final ExportsResourceImpl exports;
    private final String exportType;

    public DataExportTask(ExportsResourceImpl exports, String exportType) {
        this.exports = exports;
        this.exportType = exportType;
    }

    @Override
    public ApiResponse<T> call() throws Exception {
        ApiResponse<ExportsResourceImpl.ExportRequest> response = requestToken();

        // We have failed to get a good token...  bail
        if (!response.isSuccess()) {
            return ApiResponseFactory.error(response);
        }

        String token = response.getResult().getExportToken();

        return ApiResponseFactory.error(response.getStatus(), new ApiError(token));
    }

    private ApiResponse<ExportsResourceImpl.ExportRequest> requestToken() throws ExecutionException, InterruptedException {
        ApiResponse<ExportsResourceImpl.ExportRequest> response = exports.requestToken(exportType).get();

        // 202 - Accepted means it is in progress...
        // we don't want to have two futures working on this.
        // return it as a failure
        if (response.getStatus() == 202) {
            response = ApiResponseFactory.error(response.getStatus(), new ApiError("Export already in progress!"));
        }

        // normal success or fail, return as is.
        return response;
    }
}
