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
            getCourses();

    /**
     * Get a single course entry.
     * 
     * @param courseId
     *            the id of the course entry
     * @return a future to the response with the course
     */
    public ListenableFuture<ApiResponse<Course>>
            getCourse(int courseId);

    /**
     * Create a new course entry.
     * 
     * @param request
     *            the update request to build the course
     * @return a future to the response with the new course
     */
    public ListenableFuture<ApiResponse<Course>>
            createCourse(CourseUpdateRequest request);

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
            updateCourse(int courseId, CourseUpdateRequest request);

    /**
     * Delete a course entry.
     * 
     * @param courseId
     *            the id of the course to delete
     * @return a future to the response of success
     */
    public ListenableFuture<ApiResponse<Success>>
            deleteCourse(int courseId);
}
