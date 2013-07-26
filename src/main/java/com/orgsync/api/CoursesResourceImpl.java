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
