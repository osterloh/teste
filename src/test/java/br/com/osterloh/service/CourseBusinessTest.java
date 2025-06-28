package br.com.osterloh.service;

import br.com.osterloh.service.stub.CourseServiceStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseBusinessTest {

    @Test
    void testCoursesRelatedToSpringWhenUsingAStub() {
        ICourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        var filteredCourses = business.retrieveCoursesRelatedToSpring("John");

        assertEquals(4, filteredCourses.size());
    }

    @Test
    void testCoursesRelatedToSpringWhenUsingAFooBarStudent() {
        ICourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        var filteredCourses = business.retrieveCoursesRelatedToSpring("Foo Bar");

        assertEquals(0, filteredCourses.size());
    }

}
