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
package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.ApiResponse;
import com.orgsync.api.CoursesResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.Success;
import com.orgsync.api.model.courses.Course;
import com.orgsync.api.model.courses.CourseUpdateRequest;
import com.typesafe.config.Config;

public class CoursesIntegrationTest extends BaseIntegrationTest<CoursesResource> {

    private static final List<? extends Config> courseEntryConfig = DbTemplate.getList("course_entries");

    public CoursesIntegrationTest() {
        super(Resources.COURSES);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.COURSES);
    }

    @Test
    public void testGetCourseEntries() throws Exception {
        List<Course> results = getResult(getResource().getCourseEntries());
        testContainsIds(results, courseEntryConfig);
    }

    @Test
    public void testCourseEntryCRUD() throws Exception {
        String name = "test name";
        String link = "http://some.test.com/link";
        String date = "2013-07-01";
        String section = "211";
        int accountId = DbTemplate.getList("users").get(1).getInt("id");

        CourseUpdateRequest request = new CourseUpdateRequest().
                setAccountId(accountId).
                setName(name).
                setLink(link).
                setDate(date).
                setSection(section);

        Course created = getResult(getResource().createCourseEntry(request));

        assertEquals(name, created.getName());
        assertEquals(link, created.getLink());
        assertEquals(date, created.getDate());
        assertEquals(section, created.getSection());
        assertEquals(accountId, created.getAccountId());

        Course found = getResult(getResource().getCourseEntry(created.getId()));

        assertEquals(created, found);

        String updatedName = "new name";

        CourseUpdateRequest update = new CourseUpdateRequest().setName(updatedName);

        Course updated = getResult(getResource().updateCourseEntry(created.getId(), update));

        assertEquals(updatedName, updated.getName());

        Success result = getResult(getResource().deleteCourseEntry(updated.getId()));

        assertTrue(result.isSuccess());

        ApiResponse<Course> response = getResource().getCourseEntry(created.getId()).get();

        assertFalse(response.isSuccess());
    }
}
