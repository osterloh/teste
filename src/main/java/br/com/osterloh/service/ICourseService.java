package br.com.osterloh.service;

import java.util.List;

public interface ICourseService {

    List<String> retrieveCourses(String student);
    List<String> doSomething(String student);
}
