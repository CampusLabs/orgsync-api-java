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
import java.util.concurrent.TimeUnit;

/**
 * A task that handles the full life-cycle of getting an export.
 *
 * @author steffyj
 */
/* package */class DataExportTask<T> implements Callable<ApiResponse<T>> {

    private static final long SLEEP_TIME = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);

    private final ExportsResourceImpl exports;
    private final String exportType;

    public DataExportTask(ExportsResourceImpl exports, String exportType) {
        this.exports = exports;
        this.exportType = exportType;
    }

    @Override
    public ApiResponse<T> call() throws Exception {
            return requestToken()
                    .flatMap(fWaitForDownloadUrl())
                    .flatMap(fDownloadFile());
    }

    private BaseApiResponse<ExportsResourceImpl.ExportRequest> requestToken() throws Exception {
        BaseApiResponse<ExportsResourceImpl.ExportRequest> response = (BaseApiResponse<ExportsResourceImpl.ExportRequest>) exports.requestToken(exportType).get();

        // 202 - Accepted means it is in progress...
        // we don't want to have two futures working on this.
        // return it as a failure
        if (response.getStatus() == 202) {
            response = ApiResponseFactory.error(response.getStatus(), new ApiError("Export already in progress!"));
        }

        // normal success or fail, return as is.
        return response;
    }

    private BaseApiResponse<ExportsResourceImpl.RedeemRequest> waitForDownloadUrl(String token) throws Exception {
        BaseApiResponse<ExportsResourceImpl.RedeemRequest> response = (BaseApiResponse<ExportsResourceImpl.RedeemRequest>) exports.redeemToken(token).get();

        while (shouldRetry(response)) {
            block();
            response = (BaseApiResponse<ExportsResourceImpl.RedeemRequest>) exports.redeemToken(token).get();
        }

        // The 204 - No Content means something went wrong and there is nothing to get
        // This is a success...  but we should make it a failure
        if (response.getStatus() == 204) {
            response = ApiResponseFactory.error(response.getStatus(), new ApiError("Export has failed!"));
        }

        return response;
    }

    private BaseApiResponse<T> downloadFile(String url) {
        return ApiResponseFactory.error(200, new ApiError(url));
    }

    /**
     * Package scope to allow overriding in tests.
     *
     * @throws InterruptedException
     */
    /* package */void block() throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
    }

    private boolean shouldRetry(ApiResponse<ExportsResourceImpl.RedeemRequest> response) {
        // A response of 202 - Accepted means things are in progress
        return response.getStatus() == 202;
    }

    private MapFunction<ExportsResourceImpl.ExportRequest, BaseApiResponse<ExportsResourceImpl.RedeemRequest>> fWaitForDownloadUrl() {
        return new MapFunction<ExportsResourceImpl.ExportRequest, BaseApiResponse<ExportsResourceImpl.RedeemRequest>>() {
            @Override
            public BaseApiResponse<ExportsResourceImpl.RedeemRequest> f(ExportsResourceImpl.ExportRequest exportRequest) throws Exception {
                return waitForDownloadUrl(exportRequest.getExportToken());
            }
        };
    }

    private MapFunction<ExportsResourceImpl.RedeemRequest, BaseApiResponse<T>> fDownloadFile() {
        return new MapFunction<ExportsResourceImpl.RedeemRequest, BaseApiResponse<T>>() {
            @Override
            public BaseApiResponse<T> f(ExportsResourceImpl.RedeemRequest redeemRequest) throws Exception {
                return downloadFile(redeemRequest.getDownloadUrl());
            }
        };
    }
}
