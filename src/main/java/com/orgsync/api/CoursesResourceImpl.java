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

import static com.orgsync.api.Util.checkNotNull;

import java.util.List;

import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.courses.Course;
import com.orgsync.api.model.courses.CourseUpdateRequest;

public class CoursesResourceImpl extends BaseResource implements CoursesResource {

    CoursesResourceImpl(final ApiClientImpl client) {
        super(client, "/course_entries");
    }

    @Override
    public ListenableFuture<ApiResponse<List<Course>>> getCourseEntries() {
        return list(Course.LIST_TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Course>> getCourseEntry(final int courseId) {
        return show(courseId, Course.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Course>> createCourseEntry(final CourseUpdateRequest request) {
        checkNotNull(request);
        return create(requestToParams(request), Course.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Course>> updateCourseEntry(final int courseId, final CourseUpdateRequest request) {
        checkNotNull(request);
        return update(courseId, requestToParams(request), Course.TYPE);
    }

    @Override
    public ListenableFuture<ApiResponse<Success>> deleteCourseEntry(final int courseId) {
        return delete(courseId, Success.TYPE);
    }

    private FluentStringsMap requestToParams(final CourseUpdateRequest request) {
        FluentStringsMap params = new FluentStringsMap();

        if (request.getAccountId() != CourseUpdateRequest.NO_ACCOUNT_ID) {
            params.add("account_id", String.valueOf(request.getAccountId()));
        }

        checkAddField(params, "name", request.getName());
        checkAddField(params, "date", request.getDate());
        checkAddField(params, "link", request.getLink());
        checkAddField(params, "section", request.getSection());

        return params;
    }
}
