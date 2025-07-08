package br.com.osterloh.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;

class CourseBusinessMockWithBDDTest {

    ICourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup() {
        mockService = mock(ICourseService.class);
        business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testCoursesRelatedToSpringWhenUsingAMock() {
        given(mockService.retrieveCourses("John")).willReturn(courses);

        var filteredCourses = business.retrieveCoursesRelatedToSpring("John");

        assertThat(filteredCourses.size(), is(4));
    }

    @Test
    void testDeleteCoursesNotRelatedToSpringUsingMockitoVerifyShouldCallMethodDeleteCourse() {
        given(mockService.retrieveCourses("John")).willReturn(courses);

        business.deleteCoursesNotRelatedToSpring("John");

//        verify(mockService).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
//        verify(mockService, times(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");    //pode passar apenas uma vez
//        verify(mockService, atLeast(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");  //pode passar apenas uma vez
        verify(mockService, atLeastOnce()).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello"); //passa alguma vez
        verify(mockService).deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
        verify(mockService, never()).deleteCourse("Microsserviços do 0 com Spring Cloud, Kotlin e Docker");
    }

}
