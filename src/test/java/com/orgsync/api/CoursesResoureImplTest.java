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
    public void testGetCoursesEntries() throws Exception {
        courses.getCourseEntries();
        verifyRequest(get("/course_entries"));
    }

    @Test
    public void testGetCourseEntry() throws Exception {
        courses.getCourseEntry(123);
        verifyRequest(get("/course_entries/123"));
    }

    @Test
    public void testCreateCourseEntry() throws Exception {
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
        courses.createCourseEntry(request);

        FluentStringsMap params = new FluentStringsMap().
                add("name", name).
                add("link", link).
                add("date", date).
                add("section", section).
                add("account_id", "234");

        verifyRequest(post("/course_entries", params));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateCourseEntryNoUpdate() throws Exception {
        courses.createCourseEntry(null);
    }

    @Test
    public void testUpdateCourseEntry() throws Exception {
        String updatedName = "new name";

        CourseUpdateRequest request = new CourseUpdateRequest().setName(updatedName);
        courses.updateCourseEntry(345, request);

        verifyRequest(put("/course_entries/345", new FluentStringsMap().add("name", updatedName)));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCourseEntryNoUpdate() throws Exception {
        courses.updateCourseEntry(567, null);
    }

    @Test
    public void testDeleteCourseEntry() throws Exception {
        courses.deleteCourseEntry(789);
        verifyRequest(delete("/course_entries/789"));
    }

}
