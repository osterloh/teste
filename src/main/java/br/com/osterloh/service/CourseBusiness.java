package br.com.osterloh.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para implmentação de SUT:
 * System(Method) Under Test
 */
public class CourseBusiness {

    // ICourseService is a dependency
    private final ICourseService iCourseService;

    public CourseBusiness(ICourseService iCourseService) {
        this.iCourseService = iCourseService;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {
        var filteredCourses = new ArrayList<String>();
        if ("Foo Bar".equals(student)) {
            return filteredCourses;
        }
        var allCourses = iCourseService.retrieveCourses(student);

        for (String course : allCourses) {
            if (course.contains("Spring")) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }

    public void deleteCoursesNotRelatedToSpring(String student) {
        var allCourses = iCourseService.retrieveCourses(student);

        for (String course : allCourses) {
            if (!course.contains("Spring")) {
                iCourseService.deleteCourse(course);
            }
        }
    }
}
