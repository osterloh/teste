package br.com.osterloh.mockito;

import br.com.osterloh.service.CourseBusiness;
import br.com.osterloh.service.ICourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseBusinessMockitoInjectMocksTest {

    @Mock
    ICourseService mockService;

    @InjectMocks
    CourseBusiness business;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    List<String> courses;

    @BeforeEach
    void setup() {
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

        verify(mockService, atLeastOnce()).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello"); //passa alguma vez
        verify(mockService).deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
        verify(mockService, never()).deleteCourse("Microsserviços do 0 com Spring Cloud, Kotlin e Docker");
    }

    @Test
    void testDeleteCoursesNotRelatedToSpringUsingMockitoVerifyShouldCallMethodDeleteCourseV2() {
        given(mockService.retrieveCourses("John")).willReturn(courses);

        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
        String architetureCourse = "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#";
        String msSpringCourse = "Microsserviços do 0 com Spring Cloud, Kotlin e Docker";

        business.deleteCoursesNotRelatedToSpring("John");

        then(mockService).should().deleteCourse(agileCourse);
        then(mockService).should().deleteCourse(architetureCourse);
        then(mockService).should(never()).deleteCourse(msSpringCourse);
    }

    @Test
    void testDeleteCoursesNotRelatedToSpringCapturingArgumentsShouldCallMethodDeleteCourseV2() {
        given(mockService.retrieveCourses("John")).willReturn(courses);

        business.deleteCoursesNotRelatedToSpring("John");

        then(mockService).should(times(7)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(), is(7));
    }
}
