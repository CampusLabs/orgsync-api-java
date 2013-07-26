package com.orgsync.api;

import static com.orgsync.api.RequestParams.delete;
import static com.orgsync.api.RequestParams.get;
import static com.orgsync.api.RequestParams.post;
import static com.orgsync.api.RequestParams.put;

import org.junit.Test;

import com.ning.http.client.FluentStringsMap;
import com.orgsync.api.model.courses.CourseUpdateRequest;

public class CoursesResoureImplTest extends BaseResourceTest {

    private final CoursesResourceImpl courses = new CoursesResourceImpl(client);

    @Test
    public void testGetCourses() throws Exception {
        courses.getCourses();
        verifyRequest(get("/course_entries"));
    }

    @Test
    public void testGetCourse() throws Exception {
        courses.getCourse(123);
        verifyRequest(get("/course_entries/123"));
    }

    @Test
    public void testCreateCourse() throws Exception {
        String name = "test name";
        String link = "http://some.test.com/link";
        String date = "2013-07-01";
        String section = "211";
        int accountId = 234;

        CourseUpdateRequest request = new CourseUpdateRequest().
                setAccountId(accountId).
                setName(name).
                setLink(link).
                setDate(date).
                setSection(section);
        courses.createCourse(request);

        FluentStringsMap params = new FluentStringsMap().
                add("name", name).
                add("link", link).
                add("date", date).
                add("section", section).
                add("account_id", "234");

        verifyRequest(post("/course_entries", params));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCourseNoUpdate() throws Exception {
        courses.createCourse(null);
    }

    @Test
    public void testUpdateCourse() throws Exception {
        String updatedName = "new name";

        CourseUpdateRequest request = new CourseUpdateRequest().setName(updatedName);
        courses.updateCourse(345, request);

        verifyRequest(put("/course_entries/345", new FluentStringsMap().add("name", updatedName)));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCourseNoUpdate() throws Exception {
        courses.updateCourse(567, null);
    }

    @Test
    public void testDeleteCourse() throws Exception {
        courses.deleteCourse(789);
        verifyRequest(delete("/course_entries/789"));
    }

}
