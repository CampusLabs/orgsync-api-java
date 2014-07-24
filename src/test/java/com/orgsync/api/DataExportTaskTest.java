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
import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author steffyj
 */
public class DataExportTaskTest {

    private final ExportsResourceImpl exports = mock(ExportsResourceImpl.class);
    private final String exportType = "test";

    private final DataExportTask task = new DataExportTask(exports, exportType);

    @Test
    public void testRedeemTokenFails() throws Exception {
        ApiResponse<ExportsResourceImpl.ExportRequest> response = ApiResponseFactory.error(500, new ApiError("failed"));
        when(exports.requestToken(exportType))
                .thenReturn(new CompletedFuture<ApiResponse<ExportsResourceImpl.ExportRequest>>(response));

        assertEquals(response, task.call());
    }

    @Test
    public void testExportInProgress() throws Exception {
        ExportsResourceImpl.ExportRequest result = new ExportsResourceImpl.ExportRequest("abc123");
        ApiResponse<ExportsResourceImpl.ExportRequest> response = ApiResponseFactory.success(202, result);
        when(exports.requestToken(exportType))
                .thenReturn(new CompletedFuture<ApiResponse<ExportsResourceImpl.ExportRequest>>(response));

        assertEquals(ApiResponseFactory.error(202, new ApiError("Export already in progress!")), task.call());
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
