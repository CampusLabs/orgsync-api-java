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

import java.util.List;

import com.ning.http.client.ListenableFuture;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.courses.Course;
import com.orgsync.api.model.courses.CourseUpdateRequest;

/**
 * <p>
 * Access to the courses resource from the OrgSync API. This allows a client to get all courses and do basic CRUD
 * operations.
 * <p>
 * See: <a href="https://api.orgsync.com/api/docs/v2/courses">https://api.orgsync.com/api/docs/v2/courses</a>
 * 
 * @author steffyj
 * 
 */
public interface CoursesResource {

    /**
     * Get all of courses for this community.
     * 
     * @return a future to the response with all courses
     */
    public ListenableFuture<ApiResponse<List<Course>>>
            getCourseEntries();

    /**
     * Get a single course entry.
     * 
     * @param courseId
     *            the id of the course entry
     * @return a future to the response with the course
     */
    public ListenableFuture<ApiResponse<Course>>
            getCourseEntry(int courseId);

    /**
     * Create a new course entry.
     * 
     * @param request
     *            the update request to build the course
     * @return a future to the response with the new course
     */
    public ListenableFuture<ApiResponse<Course>>
            createCourseEntry(CourseUpdateRequest request);

    /**
     * Update a single course entry.
     * 
     * @param courseId
     *            the id of the course
     * @param request
     *            the request to update
     * @return a future to a response with the updated course
     */
    public ListenableFuture<ApiResponse<Course>>
            updateCourseEntry(int courseId, CourseUpdateRequest request);

    /**
     * Delete a course entry.
     * 
     * @param courseId
     *            the id of the course to delete
     * @return a future to the response of success
     */
    public ListenableFuture<ApiResponse<Success>>
            deleteCourseEntry(int courseId);
}
