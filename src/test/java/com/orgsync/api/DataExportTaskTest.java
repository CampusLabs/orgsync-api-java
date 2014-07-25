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

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.orgsync.api.model.ApiError;
import com.orgsync.api.model.accounts.AccountFull;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.lang.reflect.Type;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author steffyj
 */
public class DataExportTaskTest {

    private final ExportsResourceImpl exports = mock(ExportsResourceImpl.class);
    private final AsyncHttpClient client = mock(AsyncHttpClient.class);
    private final String exportType = "test";
    private final String exportToken = "abc123";
    private final Type type = AccountFull.TYPE;

    private class DataExportTaskTester extends DataExportTask {
        public int blockCalls = 0;

        public DataExportTaskTester(ExportsResourceImpl exports, String exportType, Type type) {
            super(exports, exportType, client, type);
        }

        @Override
        void block() throws InterruptedException {
            blockCalls++;
        }
    };

    private final DataExportTaskTester task = new DataExportTaskTester(exports, exportType, type);

    @Test
    public void testRequestTokenFails() throws Exception {
        ApiResponse<ExportsResourceImpl.ExportRequest> response = ApiResponseFactory.error(500, new ApiError("failed"));
        setRequestToken(response);

        assertEquals(response, task.call());
    }

    @Test
    public void testExportInProgress() throws Exception {
        ExportsResourceImpl.ExportRequest result = new ExportsResourceImpl.ExportRequest("abc123");
        ApiResponse<ExportsResourceImpl.ExportRequest> response = ApiResponseFactory.success(202, result);
        setRequestToken(response);

        assertEquals(ApiResponseFactory.error(202, new ApiError("Export already in progress!")), task.call());
    }

    @Test
    public void testRedeemFailure() throws Exception {
        setSuccessfulToken();
        int status = 500;
        String message  = "Fail!";
        setRedeemToken(false, status, message);

        ApiResponse<Object> expected = ApiResponseFactory.error(status, new ApiError(message));
        assertEquals(expected, task.call());
    }

    @Test
    public void testRedeemNoContent() throws Exception {
        setSuccessfulToken();
        int status = 204;
        setRedeemToken(true, status, null);

        ApiResponse<Object> expected = ApiResponseFactory.error(status, new ApiError("Export has failed!"));
        assertEquals(expected, task.call());
    }

    @Test
    public void testRetryOnAccepted() throws Exception {
        setSuccessfulToken();

        String url = "http://some.local/url";
        ExportsResourceImpl.RedeemRequest response = new ExportsResourceImpl.RedeemRequest(url);
        setRedeemToken(true, 202, null)
                .thenReturn(new CompletedFuture<ApiResponse<ExportsResourceImpl.RedeemRequest>>(ApiResponseFactory.success(200, response)));

        task.call();

        assertEquals(1, task.blockCalls);
    }

    @Test
    public void testDownloadFails() throws Exception {
        setSuccessfulToken();
        String url = "http://s3.local/my_file.json.gz";
        String message = "fail on download";
        setRedeemToken(true, 200, url);

        when(client.prepareGet(url)).thenThrow(new RuntimeException(message));

        assertEquals(ApiResponseFactory.error(500, "Exception caught: " + message), task.call());
    }

    private void setRequestToken(ApiResponse<ExportsResourceImpl.ExportRequest> response) {
        when(exports.requestToken(exportType))
                .thenReturn(new CompletedFuture<ApiResponse<ExportsResourceImpl.ExportRequest>>(response));
    }

    private OngoingStubbing<Future<ApiResponse<ExportsResourceImpl.RedeemRequest>>> setRedeemToken(boolean success, int status, String urlOrMessage) {
        ExportsResourceImpl.RedeemRequest result = new ExportsResourceImpl.RedeemRequest(urlOrMessage);
        ApiResponse<ExportsResourceImpl.RedeemRequest> response = null;
        if (success) {
            response = ApiResponseFactory.success(status, result);
        } else {
            response = ApiResponseFactory.error(status, new ApiError(urlOrMessage));
        }

        return when(exports.redeemToken(exportToken))
                .thenReturn(new CompletedFuture<ApiResponse<ExportsResourceImpl.RedeemRequest>>(response));
    }

    private void setSuccessfulToken() {
        ExportsResourceImpl.ExportRequest result = new ExportsResourceImpl.ExportRequest(exportToken);
        ApiResponse<ExportsResourceImpl.ExportRequest> response = ApiResponseFactory.success(200, result);
        setRequestToken(response);
    }

    static final class CompletedFuture<T> implements Future<T> {

        private final T value;

        CompletedFuture(final T value) {
            this.value = value;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public T get(final long timeout, final TimeUnit unit) {
            return value;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean cancel(final boolean mayInterruptIfRunning) {
            return false;
        }
    }


}
